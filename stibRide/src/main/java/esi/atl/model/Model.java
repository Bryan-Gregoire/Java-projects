package esi.atl.model;

import esi.atl.dto.FavoriteDto;
import esi.atl.dto.StationDto;
import esi.atl.exception.RepositoryException;
import java.util.List;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public interface Model {

    public List<StationDto> getAllStationsName() throws RepositoryException;

    public void calculateItinerary(String nameOrigin, String nameDestination) throws RepositoryException;

    public List<FavoriteDto> getAllFavorites() throws RepositoryException;

}
