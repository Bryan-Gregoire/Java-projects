package esi.atl.repository;

import esi.atl.dto.FavoriteDto;
import esi.atl.exception.RepositoryException;
import esi.atl.jdbc.FavoriteDao;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class FavoriteRepositoryTest {

    @Mock
    private FavoriteDao mock;

    private static final String KEY = "ecole";

    FavoriteDto school;
    FavoriteDto schweitzer;

    private final List<FavoriteDto> all;

    public FavoriteRepositoryTest() {
        school = new FavoriteDto(KEY, "ELISABETH", "BOTANIQUE");
        schweitzer = new FavoriteDto("laPlace", "SIMONIS", "BERCHEM");

        all = List.of(
                school,
                new FavoriteDto("maison", "BOTANIQUE", "ELISABETH"),
                new FavoriteDto("ephec", "ELISABETH", "ALMA"),
                new FavoriteDto("cinema", "ROGIER", "HEYSEL"));
    }

    @BeforeEach
    void init() throws RepositoryException {
        //Mock behaviour
        Mockito.lenient()
                .when(mock.select(school.getKey())).thenReturn(school);
        Mockito.lenient()
                .when(mock.select(schweitzer.getKey())).thenReturn(null);
        Mockito.lenient().when(mock.selectAll()).thenReturn(all);
        Mockito.lenient()
                .when(mock.select(null)).thenThrow(RepositoryException.class);
    }

    /**
     * Test of getAll method, of class FavoriteRepository.
     */
    @org.junit.jupiter.api.Test
    public void testGetAll() throws Exception {
        System.out.println("test get All");
        //Arrange
        FavoriteRepository repository = new FavoriteRepository(mock);
        //Action
        List<FavoriteDto> result = repository.getAll();
        //Assert
        assertEquals(all, result);
        Mockito.verify(mock, times(1)).selectAll();
    }

    /**
     * Test of get method, of class FavoriteRepository.
     */
    @org.junit.jupiter.api.Test
    public void testGetExist() throws Exception {
        System.out.println("test get exist");
        //Arrange
        FavoriteDto expected = school;
        FavoriteRepository repository = new FavoriteRepository(mock);
        //Action
        FavoriteDto result = repository.get(KEY);
        //Assert
        assertEquals(expected, result);
        Mockito.verify(mock, times(1)).select(KEY);
    }

    /**
     * Test of get method, of class FavoriteRepository.
     */
    @org.junit.jupiter.api.Test
    public void testGetNotExist() throws Exception {
        System.out.println("test get not exist");
        //Arrange
        FavoriteDto expected = null;
        FavoriteRepository repository = new FavoriteRepository(mock);
        //Action
        FavoriteDto result = repository.get(schweitzer.getKey());
        //Assert
        assertEquals(expected, result);
        Mockito.verify(mock, times(1)).select(schweitzer.getKey());
    }

    /**
     * Test of get method, of class FavoriteRepository.
     */
    @org.junit.jupiter.api.Test
    public void testGetInvalidParam() throws Exception {
        System.out.println("test get invalid parameter");
        //Arrange
        FavoriteRepository repository = new FavoriteRepository(mock);
        //Assert
        assertThrows(RepositoryException.class, () -> {
            repository.get(null);
        });
        Mockito.verify(mock, times(1)).select(null);
    }

    /**
     * Test of add method, of class FavoriteRepository.
     */
    @org.junit.jupiter.api.Test
    public void testAdd() throws Exception {
        System.out.println("testAddWhenExisting");
        //Arrange
        FavoriteRepository repository = new FavoriteRepository(mock);
        FavoriteDto dto = new FavoriteDto("pastaBar", "ROGIER", "SIMONIS");
        //Action
        repository.add(dto);
        //Assert
        Mockito.verify(mock, times(1)).insert(any(FavoriteDto.class));
    }

    @org.junit.jupiter.api.Test
    public void testRemove() throws Exception {
        System.out.println("test remove when existing");
        //Arrange
        FavoriteRepository repository = new FavoriteRepository(mock);
        //Action
        repository.remove(KEY);
        //Assert
        Mockito.verify(mock, times(1)).delete(school.getKey());
    }

    /**
     * Test of update method, of class FavoriteRepository.
     */
    @org.junit.jupiter.api.Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        FavoriteDto newDto = new FavoriteDto("pastaBar", "ROGIER", "SIMONIS");
        String oldKey = "cinema";
        FavoriteRepository instance = new FavoriteRepository(mock);
        instance.update(newDto, oldKey);

        Mockito.verify(mock, times(1)).update(newDto, oldKey);
    }

}
