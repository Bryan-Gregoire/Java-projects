package esi.atl.jdbc;

import esi.atl.dto.FavoriteDto;
import esi.atl.exception.RepositoryException;
import esi.atl.repository.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class FavoriteDao implements Dao<String, FavoriteDto> {

    private Connection connexion;

    private FavoriteDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();
    }

    public static FavoriteDao getInstance() throws RepositoryException {
        return FavoriteDaoHolder.getInstance();
    }

    @Override
    public List<FavoriteDto> selectAll() throws RepositoryException {
        String sql = "SELECT name, origin, destination FROM FAVORIS";

        List<FavoriteDto> listDtos = new ArrayList<>();

        try ( Statement stmt = connexion.createStatement();  ResultSet rs
                = stmt.executeQuery(sql)) {

            while (rs.next()) {
                FavoriteDto dto = new FavoriteDto(rs.getString("name"),
                        rs.getString("origin"), rs.getString("destination"));
                listDtos.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return listDtos;
    }

    @Override
    public FavoriteDto select(String key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("No key in parameter");
        }

        String sql = "SELECT  name, origin, destination FROM FAVORIS"
                + " WHERE name = ?";
        FavoriteDto dto = null;

        try ( PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, key);
            ResultSet rs = pstmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                dto = new FavoriteDto(rs.getString("name"),
                        rs.getString("origin"), rs.getString("destination"));
                count++;
            }
            if (count > 1) {
                throw new RepositoryException("Not a unique record " + key);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dto;
    }

    @Override
    public void insert(FavoriteDto dto) throws RepositoryException {
        if (dto == null) {
            throw new RepositoryException("Parameter is invalid");
        }
        String sql = "INSERT INTO FAVORIS(name,origin,destination) VALUES(?,?,?)";

        try ( PreparedStatement psmt = connexion.prepareStatement(sql)) {
            psmt.setString(1, dto.getKey());
            psmt.setString(2, dto.getOrigin());
            psmt.setString(3, dto.getDestination());
            psmt.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void update(FavoriteDto newDto, String oldKey) throws RepositoryException {
        if (newDto == null || oldKey == null) {
            throw new RepositoryException("Parameter is null");
        }

        String sql = "UPDATE FAVORIS SET name=?, origin=?, destination=? "
                + "WHERE name=?";

        try ( PreparedStatement psmt = connexion.prepareStatement(sql)) {
            psmt.setString(1, newDto.getKey());
            psmt.setString(2, newDto.getOrigin());
            psmt.setString(3, newDto.getDestination());
            psmt.setString(4, oldKey);

            psmt.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void delete(String key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("Parameter is null");
        }

        String sql = "DELETE FROM FAVORIS WHERE name=?";
        try ( PreparedStatement psmt = connexion.prepareStatement(sql)) {
            psmt.setString(1, key);
            psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    private static class FavoriteDaoHolder {

        private static FavoriteDao getInstance() throws RepositoryException {
            return new FavoriteDao();
        }
    }
}
