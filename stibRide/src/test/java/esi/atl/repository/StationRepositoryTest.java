package esi.atl.repository;

import esi.atl.dto.StationDto;
import esi.atl.exception.RepositoryException;
import esi.atl.jdbc.StibDao;
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
    private StibDao mock;

    private static final int KEY = 8764;

    private final StationDto simonis;
    private final StationDto schweitzer;

    private final List<StationDto> all;

    public StationRepositoryTest() {

        simonis = new StationDto(KEY, "SIMONIS");
        schweitzer = new StationDto(99_999, "SCHWEITZER");

        all = List.of(new StationDto(8012, "DE BROUCKERE"),
                new StationDto(8022, "GARE CENTRALE"),
                new StationDto(8032, "PARC"),
                new StationDto(8042, "ARTS-LOI"),
                new StationDto(8052, "MAELBEEK"),
                new StationDto(8062, "SCHUMAN"),
                new StationDto(8072, "MERODE"),
                new StationDto(8082, "MONTGOMERY"),
                new StationDto(8092, "JOSEPH.-CHARLOTTE"),
                new StationDto(8102, "GRIBAUMONT"),
                new StationDto(8112, "TOMBERG"),
                new StationDto(8122, "ROODEBEEK"),
                new StationDto(8132, "VANDERVELDE"),
                new StationDto(8142, "ALMA"),
                new StationDto(8152, "CRAINHEM"),
                new StationDto(8161, "STOCKEL"),
                new StationDto(8202, "THIEFFRY"),
                new StationDto(8212, "PETILLON"),
                new StationDto(8222, "HANKAR"),
                new StationDto(8232, "DELTA"),
                new StationDto(8242, "BEAULIEU"),
                new StationDto(8252, "DEMEY"),
                new StationDto(8262, "HERRMANN-DEBROUX"),
                new StationDto(8272, "SAINTE-CATHERINE"),
                new StationDto(8282, "COMTE DE FLANDRE"),
                new StationDto(8292, "ETANGS NOIRS"),
                new StationDto(8302, "TRONE"),
                new StationDto(8312, "PORTE DE NAMUR"),
                new StationDto(8322, "LOUISE"),
                new StationDto(8332, "HOTEL DES MONNAIES"),
                new StationDto(8342, "PORTE DE HAL"),
                new StationDto(8352, "GARE DU MIDI"),
                new StationDto(8362, "CLEMENCEAU"),
                new StationDto(8372, "DELACROIX"),
                new StationDto(8382, "GARE DE L''OUEST"),
                new StationDto(8412, "MADOU"),
                new StationDto(8422, "BOTANIQUE"),
                new StationDto(8432, "ROGIER"),
                new StationDto(8442, "YSER"),
                new StationDto(8462, "RIBAUCOURT"),
                new StationDto(8472, "ELISABETH"),
                new StationDto(8641, "ERASME"),
                new StationDto(8652, "EDDY MERCKX"),
                new StationDto(8662, "CERIA"),
                new StationDto(8672, "LA ROUE"),
                new StationDto(8682, "BIZET"),
                new StationDto(8692, "VEEWEYDE"),
                new StationDto(8702, "SAINT-GUIDON"),
                new StationDto(8712, "AUMALE"),
                new StationDto(8722, "JACQUES BREL"),
                new StationDto(8742, "BEEKKANT"),
                new StationDto(8754, "OSSEGHEM"),
                simonis,
                new StationDto(8774, "BELGICA"),
                new StationDto(8784, "PANNENHUIS"),
                new StationDto(8794, "BOCKSTAEL"),
                new StationDto(8804, "STUYVENBERGH"),
                new StationDto(8814, "HOUBA-BRUGMANN"),
                new StationDto(8824, "HEYSEL"),
                new StationDto(8833, "ROI BAUDOUIN"));
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
