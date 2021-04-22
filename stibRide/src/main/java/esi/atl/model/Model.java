package esi.atl.model;

import esi.atl.dto.StationDto;
import esi.atl.exception.RepositoryException;
import java.util.List;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public interface Model {

    public List<StationDto> getAllStationsName() throws RepositoryException;

    public List<StationDto> getFullStation() throws RepositoryException;

}
