package esi.atl.presenter;

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
        view.hideEmptyLbl();
    }

    public void getItinerary() throws RepositoryException {
        view.hideEmptyLbl();
        try {
            String origin = view.getOrigin();
            String destination = view.getDestination();
            model.calculateItinerary(origin, destination);
        } catch (Exception e) {
            view.showEmptyLbl();
        }
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
