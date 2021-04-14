package esi.atl.repository;

import esi.atl.dto.StationDto;
import esi.atl.exception.RepositoryException;
import esi.atl.jdbc.StibDao;
import java.util.List;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class StationRepository implements Repository<Integer, StationDto> {

    private final StibDao dao;

    public StationRepository() throws RepositoryException {
        dao = StibDao.getInstance();
    }

    StationRepository(StibDao dao) {
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
}
