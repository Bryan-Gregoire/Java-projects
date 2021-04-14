/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atl.jdbc;

import esi.atl.config.ConfigManager;
import esi.atl.dto.StationDto;
import esi.atl.exception.RepositoryException;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class StibDaoTest {

    private static final int KEY = 8764;

    private final StationDto simonis;
    private final StationDto schweitzer;

    private final List<StationDto> all;

    private StibDao instance;

    public StibDaoTest() {
        System.out.println("==== StationsDto Constructor =====");

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
        try {
            ConfigManager.getInstance().load();
            instance = StibDao.getInstance();
        } catch (RepositoryException | IOException ex) {
            org.junit.jupiter.api.Assertions.fail("Erreur de connection à la"
                    + " base de données de test", ex);
        }
    }

    /**
     * Test of selectAll method, of class StibDao.
     * @throws java.lang.Exception
     */
    @Test
    public void testSelectAll() throws Exception {
        System.out.println("Test selectAll");
        List<StationDto> result = instance.selectAll();
        assertEquals(all, result);
    }

    /**
     * Test of select method, of class StibDao.
     * @throws java.lang.Exception
     */
    @Test
    public void testSelectExist() throws Exception {
        System.out.println("test select");
        StationDto result = instance.select(KEY);
        assertEquals(simonis, result);
    }

    /**
     * Test of select method, of class StibDao.
     * @throws java.lang.Exception
     */
    @Test
    public void testSelectNotExist() throws Exception {
        System.out.println("test select");
        StationDto result = instance.select(schweitzer.getKey());
        assertNull(result);
    }

}
