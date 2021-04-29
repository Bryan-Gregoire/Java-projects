package esi.atl.repository;

import esi.atl.dto.FavoriteDto;
import esi.atl.exception.RepositoryException;
import esi.atl.jdbc.FavoriteDao;
import java.util.List;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class FavoriteRepository implements Repository<Integer, FavoriteDto> {

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
    public FavoriteDto get(Integer key) throws RepositoryException {
        FavoriteDto dto = dao.select(key);
        return dto;
    }

    @Override
    public Integer add(FavoriteDto dto) throws RepositoryException {
        Integer key = dto.getKey();
        if (contains(key)) {
            dao.update(dto);
        } else {
            return dao.insert(dto);
        }
        return -1;
    }

    @Override
    public void remove(Integer key) throws RepositoryException {
        dao.delete(key);
    }

    public void update(FavoriteDto dto) throws RepositoryException {
        dao.update(dto);
    }

    @Override
    public boolean contains(Integer key) throws RepositoryException {
        FavoriteDto dto = dao.select(key);
        return dto != null;
    }

}
