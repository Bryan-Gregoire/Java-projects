package esi.atl.model;

import esi.atl.dto.StationDto;
import esi.atl.exception.RepositoryException;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public interface Model {

    public void addPropertyChangeListener(PropertyChangeListener listener);
    
    public List<StationDto> getAllStations() throws RepositoryException;
}
