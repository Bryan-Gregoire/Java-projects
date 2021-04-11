package esi.atl.jdbc;

import esi.atl.dto.StationDto;
import esi.atl.exception.RepositoryException;
import esi.atl.repository.Dao;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class StibDao implements Dao<Integer, StationDto> {

    private Connection connexion;

    private StibDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();

    }

    public static StibDao getInstance() throws RepositoryException {
        return StibDaoHolder.getInstance();
    }

    @Override
    public List<StationDto> selectAll() throws RepositoryException {
        // TODO
        return null;
    }

    @Override
    public StationDto select(Integer key) throws RepositoryException {
        // TODO 
        return null;
    }

    private static class StibDaoHolder {

        private static StibDao getInstance() throws RepositoryException {
            return new StibDao();
        }
    }

}
