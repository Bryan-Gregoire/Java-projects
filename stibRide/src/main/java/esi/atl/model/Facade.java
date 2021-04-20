package esi.atl.model;

import esi.atl.dto.StationDto;
import esi.atl.exception.RepositoryException;
import esi.atl.repository.StationRepository;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class Facade implements Model {

    private final PropertyChangeSupport pcs;

    private final StationRepository repo;

    public Facade() throws RepositoryException {
        this.pcs = new PropertyChangeSupport(this);
        this.repo = new StationRepository();
    }

    @Override
    public List getAllStations() throws RepositoryException {
        List<StationDto> dtos = repo.getAll();
        List nameStation = new ArrayList();
        for (StationDto dto : dtos) {
            String station = dto.getName();
            nameStation.add(station);
        }
        return nameStation;
    }

    /**
     * Add listener
     *
     * @param listener the listener.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    /**
     * Remove listener.
     *
     * @param listener the listener.
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        pcs.removePropertyChangeListener(listener);
    }
}
