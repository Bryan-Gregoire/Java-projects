package esi.atl.jdbc;

import esi.atl.dto.StationDto;
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
        String sql = "SELECT id, name  FROM STATIONS";
        ArrayList<StationDto> listDtos = new ArrayList<>();
        try (Statement stmt = connexion.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                StationDto dto = new StationDto(rs.getInt("id"),
                        rs.getString("name"));
                listDtos.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return listDtos;
    }

    @Override
    public StationDto select(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("Aucune clé donnée en paramètre");
        }
        
        String sql = "SELECT id, name FROM STATIONS WHERE  id = ?";
        StationDto dto = null;

        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setInt(1, key);
            ResultSet rs = pstmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                dto = new StationDto(rs.getInt(1), rs.getString(2));
                count++;
            }
            if (count > 1) {
                throw new RepositoryException("Record pas unique " + key);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dto;
    }

    private static class StibDaoHolder {

        private static StibDao getInstance() throws RepositoryException {
            return new StibDao();
        }
    }

}
