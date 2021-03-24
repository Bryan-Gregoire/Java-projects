package atl.grade.jdbc;

import atl.grade.dto.GradeDto;
import atl.grade.dto.StudentDto;
import atl.grade.exception.RepositoryException;
import atl.grade.repository.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jlc
 */
public class StudentsDao implements Dao<Integer, StudentDto> {

    private Connection connexion;

    private StudentsDao() throws RepositoryException {
        connexion = DBManager.getInstance().getConnection();

    }

    public static StudentsDao getInstance() throws RepositoryException {
        return StudentsDaoHolder.getInstance();
    }

    @Override
    public Integer insert(StudentDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("Aucune élément donné en paramètre");
        }
        Integer id = 0;
        String sql = "INSERT INTO STUDENTS(lastname,firstname) values(?, ? )";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, item.getLastName());
            pstmt.setString(2, item.getFirstName());
            pstmt.executeUpdate();

            ResultSet result = pstmt.getGeneratedKeys();
            while (result.next()) {
                id = result.getInt(1);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return id;
    }

    @Override
    public void delete(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("Aucune clé donnée en paramètre");
        }
        String sql = "DELETE FROM STUDENTS WHERE id = ?";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setInt(1, key);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void update(StudentDto item) throws RepositoryException {
        if (item == null) {
            throw new RepositoryException("Aucune élément donné en paramètre");
        }
        String sql = "UPDATE STUDENTS SET firstname=? ,lastName=? where id=? ";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setString(1, item.getFirstName());
            pstmt.setString(2, item.getLastName());
            pstmt.setInt(3, item.getKey());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<StudentDto> selectAll() throws RepositoryException {
        String sql = "SELECT id,firstname,lastname FROM STUDENTS";
        List<StudentDto> dtos = new ArrayList<>();
        try (Statement stmt = connexion.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                StudentDto dto = new StudentDto(rs.getInt(1), rs.getString(2), rs.getString(3));
                dtos.add(dto);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return dtos;
    }

    @Override
    public StudentDto select(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("Aucune clé donnée en paramètre");
        }
        String sql = "SELECT id,lastname,firstname FROM STUDENTS WHERE  id = ?";
        StudentDto dto = null;
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setInt(1, key);
            ResultSet rs = pstmt.executeQuery();

            int count = 0;
            while (rs.next()) {
                dto = new StudentDto(rs.getInt(1), rs.getString(2), rs.getString(3));
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

    public StudentDto getFullStudent(Integer key) throws RepositoryException {
        if (key == null) {
            throw new RepositoryException("Aucune clé donnée en paramètre ");
        }
        StudentDto student = select(key);
        //String sql = "SELECT G.id_lesson,G.score FROM GRADES G JOIN STUDENTS S ON G.id_student =" + key;
        String sql = "SELECT id_lesson,score FROM GRADES where id_student = " + key;

        try (Statement stmt = connexion.createStatement();
                ResultSet result = stmt.executeQuery(sql)) {
            while (result.next()) {
                String lesson = result.getString(1);
                int value = result.getInt(2);
                System.out.println("La lecon : " + lesson + " Le score : " + value);
                student.getGrades().add(new GradeDto(key, value, lesson));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }

        return student;
    }

    private static class StudentsDaoHolder {

        private static StudentsDao getInstance() throws RepositoryException {
            return new StudentsDao();
        }
    }
}
