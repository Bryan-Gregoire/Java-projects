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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class StibDaoTest {

    private final StationDto simonis;
    private final StationDto schweitzer;

    private static final int KEY = 8764;

    private final List<StationDto> all;

    private StibDao instance;

    public StibDaoTest() {
        simonis = new StationDto(KEY, "SIMONIS");
        schweitzer = new StationDto(99_999, "SCHWEITZER");

        all = List.of(
                new StationDto(8032, "PARC"),
                new StationDto(8422, "BOTANIQUE"),
                new StationDto(8432, "ROGIER"),
                simonis,
                new StationDto(8774, "BELGICA"));
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
     *
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
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSelectExist() throws Exception {
        System.out.println("test select exist");
        StationDto result = instance.select(KEY);
        assertEquals(simonis, result);
    }

    /**
     * Test of select method, of class StibDao.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSelectNotExist() throws Exception {
        System.out.println("test select not exist");
        StationDto result = instance.select(schweitzer.getKey());
        assertNull(result);
    }

    /**
     * Test of select method, of class StibDao.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSelectInvalidParameter() throws Exception {
        System.out.println("test select invalid parameter");
        Integer incorrect = null;
        assertThrows(RepositoryException.class, () -> {
            StationDto result = instance.select(incorrect);
        });

    }
}
