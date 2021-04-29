package esi.atl.repository;

import esi.atl.dto.StationDto;
import esi.atl.dto.StopDto;
import esi.atl.exception.RepositoryException;
import esi.atl.jdbc.StationDao;
import java.util.List;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class StationRepository implements Repository<Integer, StationDto> {

    private final StationDao dao;

    public StationRepository() throws RepositoryException {
        dao = StationDao.getInstance();
    }

    StationRepository(StationDao dao) {
        this.dao = dao;
    }

    @Override
    public List<StationDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }

    @Override
    public StationDto get(Integer key) throws RepositoryException {
        StationDto refreshItem = dao.select(key);
        return refreshItem;
    }

    public List<StopDto> getAllStops() throws RepositoryException {
        return dao.getFullStop();
    }

    @Override
    public Integer add(StationDto item) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove(Integer key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean contains(Integer key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
