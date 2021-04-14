package esi.atl.presenter;

import esi.atl.dto.StationDto;
import esi.atl.exception.RepositoryException;
import esi.atl.model.Model;
import esi.atl.repository.StationRepository;
import esi.atl.view.View;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class Presenter implements PropertyChangeListener {

    private final Model model;
    private final View view;
    private StationRepository repo;

    public Presenter(Model model, View view) throws RepositoryException {
        this.model = model;
        this.view = view;
        repo = new StationRepository();
    }

    public void initialise() throws RepositoryException {
        List<StationDto> stations = repo.getAll();
        view.fillSearchableComboBox(stations);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //TODO
    }

}
