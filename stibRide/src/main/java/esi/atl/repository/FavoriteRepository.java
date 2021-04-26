package esi.atl.repository;

import esi.atl.dto.FavoriteDto;
import esi.atl.exception.RepositoryException;
import esi.atl.jdbc.FavoriteDao;
import java.util.List;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class FavoriteRepository implements Repository<String, FavoriteDto> {

    private FavoriteDao dao;

    public FavoriteRepository() throws RepositoryException {
        dao = FavoriteDao.getInstance();
    }

    FavoriteRepository(FavoriteDao dao) {
        this.dao = dao;
    }

    @Override
    public List<FavoriteDto> getAll() throws RepositoryException {
        return dao.selectAll();
    }

    @Override
    public FavoriteDto get(String key) throws RepositoryException {
        FavoriteDto dto = dao.select(key);
        return dto;
    }

    public void add(FavoriteDto dto) throws RepositoryException {
        String key = dto.getKey();
        if (contains(key)) {
            dao.update(dto);
        } else {
            dao.insert(dto);
        }
    }

    public void remove(String key) throws RepositoryException {
        dao.delete(key);
    }

    public boolean contains(String key) throws RepositoryException {
        FavoriteDto dto = dao.select(key);
        return dto != null;
    }

}
