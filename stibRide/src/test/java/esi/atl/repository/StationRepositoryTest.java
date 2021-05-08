package esi.atl.repository;

import esi.atl.dto.StationDto;
import esi.atl.exception.RepositoryException;
import esi.atl.jdbc.StationDao;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
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
public class StationRepositoryTest {

    @Mock
    private StationDao mock;

    private static final int KEY = 8764;

    private final StationDto simonis;
    private final StationDto schweitzer;

    private final List<StationDto> all;

    public StationRepositoryTest() {
        simonis = new StationDto(KEY, "SIMONIS");
        schweitzer = new StationDto(99_999, "SCHWEITZER");

        all = List.of(
                new StationDto(8032, "PARC"),
                new StationDto(8422, "BOTANIQUE"),
                new StationDto(8432, "ROGIER"),
                simonis,
                new StationDto(8774, "BELGICA"));
    }

    @BeforeEach
    void init() throws RepositoryException {
        //Mock behaviour
        Mockito.lenient()
                .when(mock.select(simonis.getKey())).thenReturn(simonis);
        Mockito.lenient()
                .when(mock.select(schweitzer.getKey())).thenReturn(null);
        Mockito.lenient().when(mock.selectAll()).thenReturn(all);
        Mockito.lenient()
                .when(mock.select(null)).thenThrow(RepositoryException.class);
    }

    /**
     * Test of getAll method, of class StationRepository.
     */
    @Test
    public void testGetAll() throws Exception {
        System.out.println("test get All");
        //Arrange
        StationRepository repository = new StationRepository(mock);
        //Action
        List<StationDto> result = repository.getAll();
        //Assert
        assertEquals(all, result);
        Mockito.verify(mock, times(1)).selectAll();
    }

    /**
     * Test of get method, of class StationRepository.
     */
    @Test
    public void testGetExist() throws Exception {
        System.out.println("test get exist");
        //Arrange
        StationDto expected = simonis;
        StationRepository repository = new StationRepository(mock);
        //Action
        StationDto result = repository.get(KEY);
        //Assert
        assertEquals(expected, result);
        Mockito.verify(mock, times(1)).select(KEY);
    }

    /**
     * Test of get method, of class StationRepository.
     */
    @Test
    public void testGetNotExist() throws Exception {
        System.out.println("test get not exist");
        //Arrange
        StationDto expected = null;
        StationRepository repository = new StationRepository(mock);
        //Action
        StationDto result = repository.get(schweitzer.getKey());
        //Assert
        assertEquals(expected, result);
        Mockito.verify(mock, times(1)).select(schweitzer.getKey());
    }

    /**
     * Test of get method, of class StationRepository.
     */
    @Test
    public void testGetInvalidParam() throws Exception {
        System.out.println("test get invalid parameter");
        //Arrange
        StationRepository repository = new StationRepository(mock);
        //Assert
        assertThrows(RepositoryException.class, () -> {
            repository.get(null);
        });
        Mockito.verify(mock, times(1)).select(null);
    }

}
