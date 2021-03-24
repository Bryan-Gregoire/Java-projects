package atl.grade.jdbc;

import atl.grade.config.ConfigManager;
import atl.grade.dto.GradeDto;
import atl.grade.dto.StudentDto;
import atl.grade.exception.RepositoryException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *
 * @author jlc
 */
public class StudentsDaoTest {

    private final StudentDto bob;
    private final StudentDto patrick;

    private static final int KEY = 6;

    private final List<StudentDto> all;

    private StudentsDao instance;

    public StudentsDaoTest() {
        System.out.println("==== StudentDaoTest Constructor =====");
        bob = new StudentDto(KEY, "SquarePants", "SpongeBob");
        patrick = new StudentDto(99_999, "Star", "Patrick");

        all = new ArrayList<>();
        all.add(new StudentDto(1, "Olsen", "Maggy"));
        all.add(new StudentDto(2, "Frost", "Phoebe"));
        all.add(new StudentDto(3, "Ortega", "Skyler"));
        all.add(new StudentDto(4, "Blankenship", "Byron"));
        all.add(new StudentDto(5, "Cote", "Molly"));
        all.add(bob);

        try {
            ConfigManager.getInstance().load();
            instance = StudentsDao.getInstance();
        } catch (RepositoryException | IOException ex) {
            org.junit.jupiter.api.Assertions.fail("Erreur de connection à la base de données de test", ex);
        }
    }

    @Test
    public void testSelectExist() throws Exception {
        System.out.println("testSelectExist");
        //Arrange
        StudentDto expected = bob;
        //Action
        StudentDto result = instance.select(KEY);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    public void testSelectNotExist() throws Exception {
        System.out.println("testSelectNotExist");
        //Arrange
        //Action
        StudentDto result = instance.select(patrick.getKey());
        //Assert
        assertNull(result);
    }

    @Test
    public void testSelectIncorrectParameter() throws Exception {
        System.out.println("testSelectIncorrectParameter");
        //Arrange
        Integer incorrect = null;
        //Assert
        assertThrows(RepositoryException.class, () -> {
            //Action
            instance.select(incorrect);
        });
    }

    @Test
    public void testGetFullStudent() throws Exception {
        System.out.println("Test GetFullStudent exist");
        //Arrange
        StudentDto excepted = all.get(0);

        List<GradeDto> exceptedGrades = new ArrayList<GradeDto>();
        exceptedGrades.add(new GradeDto(1, 14, "ATL"));
        exceptedGrades.add(new GradeDto(1, 16, "ATL"));
        exceptedGrades.add(new GradeDto(1, 10, "ATL"));

        //Action
        StudentDto result = instance.getFullStudent(1);
        List<GradeDto> resultGrades = result.getGrades();
        //Assert
        assertEquals(excepted, result);
        assertEquals(exceptedGrades, resultGrades);
    }

    @Test
    public void testGetFullStudent2() throws Exception {
        System.out.println("Test GetFullStudent exist");
        //Arrange
        StudentDto excepted = all.get(1);

        List<GradeDto> exceptedGrades = new ArrayList<GradeDto>();
        exceptedGrades.add(new GradeDto(2, 14, "ATL"));
        exceptedGrades.add(new GradeDto(2, 14, "ATL"));

        //Action
        StudentDto result = instance.getFullStudent(2);
        List<GradeDto> resultGrades = result.getGrades();
        //Assert
        assertEquals(excepted, result);
        assertEquals(exceptedGrades, resultGrades);
    }

    @Test
    public void testGetFullStudentNotExist() throws Exception {
        System.out.println("Test GetFullStudent not exist");
        //Arrange
        //Action
        StudentDto result = instance.getFullStudent(patrick.getKey());
        //Assert
        assertNull(result);
    }

    @Test
    public void testGetFullStudentIncorrectParameter() throws Exception {
        System.out.println("testGetFullStudentIncorrectParameter");
        //Arrange
        Integer incorrect = null;
        //Assert
        assertThrows(RepositoryException.class, () -> {
            //Action
            instance.getFullStudent(incorrect);
        });
    }

}
