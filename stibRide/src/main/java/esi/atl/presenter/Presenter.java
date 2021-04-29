package esi.atl.presenter;

import esi.atl.dto.FavoriteDto;
import esi.atl.exception.RepositoryException;
import esi.atl.model.Facade;
import esi.atl.model.Model;
import esi.atl.model.StationData;
import esi.atl.view.View;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class Presenter implements PropertyChangeListener {

    private final Model model;
    private final View view;

    public Presenter(Model model, View view) throws RepositoryException {
        Objects.requireNonNull(model, "Model is required");
        Objects.requireNonNull(view, "View is required");
        this.model = model;
        this.view = view;
    }

    public void initialise() throws RepositoryException {
        List stations = model.getAllStationsName();
        view.fillSearchableComboBox(stations);
        view.addSearchHandler(this);
        view.addInsertHandler(this);
        view.addDeleteHandler(this);
        List<FavoriteDto> favorites = model.getAllFavorites();
        view.addAllFavToTable(favorites);
    }

    public void getItinerary() throws RepositoryException {
        view.hideEmptyStationLbl();
        try {
            String origin = view.getOrigin();
            String destination = view.getDestination();
            model.calculateItinerary(origin, destination);
        } catch (Exception e) {
            view.showEmptyStationLbl();
        }
    }

    public void insertUpdateFav() throws RepositoryException {
        if (view.isFavTextEmpty()) {
            view.showEmptyFavLbl();
        } else {
            String fav = view.getFavText();
            String origin = view.getOrigin();
            String destination = view.getDestination();
            FavoriteDto dto = new FavoriteDto(fav, origin, destination);
            model.insertFavorite(dto);
            if (!view.containFav(dto)) { //Si il existe deja
                view.addFavToTable(dto);
            } else {
                // je fait un update
                for (FavoriteDto favoritesData : view.getAllFavorites()) {
                    if (favoritesData.getKey().equals(dto.getKey())) {
                        favoritesData.setOrigin(dto.getOrigin());
                        favoritesData.setDestination(dto.getDestination());
                        break;
                    }
                }
            }
            view.hideEmptyFavLbl();
        }
    }

    public void deleteFavorite() throws RepositoryException {
        if (view.isFavTextEmpty()) {
            view.showEmptyFavLbl();
        } else {
            String key = view.getFavText();
            model.deleteFavorite(key);
            FavoriteDto fav = getFav(key);//Pour voir si j'ai supp quelque chose. (je pourrait simplifier ca dans le DAO en retournant le nombre de modification)
            if (fav != null) {
                view.removeFavFromTable(fav);
            }
            view.hideEmptyFavLbl();
        }
    }

    public FavoriteDto getFav(String key) {
        for (FavoriteDto favorite : view.getAllFavorites()) {
            if (favorite.getKey().equals(key)) {
                return favorite;
            }
        }
        return null;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Facade.SHORT_PATH)) {
            view.addIteneraryData((List<StationData>) evt.getNewValue());
            view.setLblStatusText("Recherche terminée");
            view.setLblNbStationText("Nombre de stations : " + view.getNbStation());
        }
    }

}
