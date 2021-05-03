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
        List<FavoriteDto> favorites = model.getAllFavorites();
        view.addAllFavToTable(favorites);
        view.addSearchHandler(this);
        view.addInsertHandler(this);
        view.addDeleteHandler(this);
        view.addUpdateHandler(this);
        view.addFavTableMouseClicked(this);
    }

    public void setSelectedFavToFavField() {
        try {
            FavoriteDto dto = view.getSelectedFav();
            view.setFavText(dto.getKey());
        } catch (Exception e) {
        }
    }

    public void getItinerary() throws RepositoryException {
        if (view.selectedStationsIsEmpty()) {
            view.showEmptyStationLbl();
        } else {
            view.hideEmptyStationLbl();
            String origin = view.getOrigin();
            String destination = view.getDestination();
            model.calculateItinerary(origin, destination);
        }
    }

    public void insertFav() throws RepositoryException {
        view.hideEmptyStationLbl();
        view.hideFavSolution();
        if (view.selectedStationsIsEmpty()) {
            view.showEmptyStationLbl();
        } else if (view.isFavTextEmpty()) {
            view.showFavSolution();
        } else {
            String fav = view.getFavTextField();
            String origin = view.getOrigin();
            String destination = view.getDestination();
            FavoriteDto dto = new FavoriteDto(fav, origin, destination);
            try {
                model.insertFavorite(dto);
            } catch (RepositoryException e) {
                view.showFavNameExistAlert();
            }
        }
    }

    public void deleteFavorite() throws RepositoryException {
        FavoriteDto dto = view.getSelectedFav();
        if (dto != null) {
            model.deleteFavorite(dto);
        }
    }

    public void updateFavorite() throws RepositoryException {
        if (view.isFavTextEmpty()) {
            view.showFavSolution();
        } else if (view.selectedStationsIsEmpty()) {
            view.showEmptyStationLbl();
        } else {
            FavoriteDto selectFav = view.getSelectedFav();
            String fav = view.getFavTextField();
            String origin = view.getOrigin();
            String destination = view.getDestination();
            FavoriteDto newDto = new FavoriteDto(fav, origin, destination);
            if (selectFav != null) {
                try {
                    model.updateFavorite(selectFav, newDto);
                } catch (Exception e) {
                    view.showFavNameExistAlert();
                }
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Facade.SHORT_PATH)) {
            view.addIteneraryData((List<StationData>) evt.getNewValue());
            view.setLblStatusText("Recherche terminée");
            view.setLblNbStationText("Nombre de stations : "
                    + view.getNbStation());
        }
        if (evt.getPropertyName().equals(Facade.INSERT_FAV)) {
            this.view.addFavToTable((FavoriteDto) evt.getNewValue());
        }
        if (evt.getPropertyName().equals(Facade.DELETE_FAV)) {
            this.view.removeFavFromTable((FavoriteDto) evt.getNewValue());
        }
        if (evt.getPropertyName().equals(Facade.UPDATE_FAV)) {
            this.view.removeFavFromTable((FavoriteDto) evt.getOldValue());
            this.view.addFavToTable((FavoriteDto) evt.getNewValue());
        }
    }

}
