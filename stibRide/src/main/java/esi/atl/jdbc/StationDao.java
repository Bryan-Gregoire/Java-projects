package esi.atl.jdbc;

import esi.atl.dto.StationDto;
import esi.atl.dto.StopDto;
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
public class StationDao implements Dao<Integer, StationDto> {

    private final Connection connexion;

    private StationDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();
    }

    public static StationDao getInstance() throws RepositoryException {
        return StationDaoHolder.getInstance();
    }

    @Override
    public List<StationDto> selectAll() throws RepositoryException {
        String sql = "SELECT id, name  FROM STATIONS order by id";

        List<StationDto> listDtos = new ArrayList<>();

        try ( Statement stmt = connexion.createStatement();  ResultSet rs
                = stmt.executeQuery(sql)) {

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
            throw new RepositoryException("No key given in parameter");
        }

        String sql = "SELECT id, name FROM STATIONS WHERE  id = ?";
        StationDto dto = null;

        try ( PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setInt(1, key);
            ResultSet rs = pstmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                dto = new StationDto(rs.getInt(1), rs.getString(2));
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

    public List<StopDto> getFullStop() throws RepositoryException {

        String sql = "SELECT id_line, id_station, id_order from STOPS "
                + "order by id_station";

        List<StopDto> dtos = new ArrayList();
        try ( Statement stmt = connexion.createStatement();  ResultSet rs
                = stmt.executeQuery(sql)) {

            while (rs.next()) {
                dtos.add(new StopDto(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
            }

        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dtos;
    }

    @Override
    public void insert(StationDto dto) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(StationDto dto, Integer key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Integer key) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static class StationDaoHolder {

        private static StationDao getInstance() throws RepositoryException {
            return new StationDao();
        }
    }

}
