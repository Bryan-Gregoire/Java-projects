/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.atl.jdbc;

import esi.atl.config.ConfigManager;
import esi.atl.dto.FavoriteDto;
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
public class FavoriteDaoTest {

    private static final String KEY = "ecole";

    FavoriteDto school;
    FavoriteDto schweitzer;

    private final List<FavoriteDto> all;

    private FavoriteDao instance;

    public FavoriteDaoTest() {
        school = new FavoriteDto(KEY, "ELISABETH", "BOTANIQUE");
        schweitzer = new FavoriteDto("schweitzer", "SIMONIS", "BERCHEM");

        all = List.of(
                school,
                new FavoriteDto("maison", "BOTANIQUE", "ELISABETH"),
                new FavoriteDto("ephec", "ELISABETH", "ALMA"),
                new FavoriteDto("cinema", "ROGIER", "HEYSEL"));
        try {
            ConfigManager.getInstance().load();
            instance = FavoriteDao.getInstance();
        } catch (RepositoryException | IOException ex) {
            org.junit.jupiter.api.Assertions.fail("Erreur de connection à la"
                    + " base de données de test", ex);
        }
    }

//    @BeforeEach
//    void init() throws RepositoryException {
//        try {
//            ConfigManager.getInstance().load();
//            instance = FavoriteDao.getInstance();
//        } catch (RepositoryException | IOException ex) {
//            org.junit.jupiter.api.Assertions.fail("Erreur de connection à la"
//                    + " base de données de test", ex);
//        }
//    }
    /**
     * Test of selectAll method, of class FavoriteDao.
     */
    @Test
    public void TestSelectAll() throws Exception {
        System.out.println("selectAll");
        List<FavoriteDto> result = instance.selectAll();
        assertEquals(all, result);
    }

    /**
     * Test of select method, of class FavoriteDao.
     */
    @Test
    public void testSelectExist() throws Exception {
        System.out.println("select exist");
        FavoriteDto result = instance.select(KEY);
        assertEquals(school, result);
    }

    /**
     * Test of select method, of class FavoriteDao.
     */
    @Test
    public void testSelectNotExist() throws Exception {
        System.out.println("select not exist");
        FavoriteDto result = instance.select(schweitzer.getKey());
        assertNull(result);
    }

    /**
     * Test of select method, of class FavoriteDao.
     */
    @Test
    public void testSelectInvalidParameter() throws Exception {
        System.out.println("select invalid parameter");
        String incorrect = null;
        assertThrows(RepositoryException.class, () -> {
            FavoriteDto result = instance.select(incorrect);
        });
    }

//    /**
//     * Test of insert method, of class FavoriteDao.
//     */
//    @Test
//    public void TestInsertExist() throws Exception {
//        System.out.println(" Test insert exist");
//
//        FavoriteDto item = new FavoriteDto("ville", "SCHWEITZER", "ROGIER");
//
//        instance.insert(item);
//
//        assertEquals(instance.select(item.getKey()),
//                new FavoriteDto("ville", "SCHWEITZER", "ROGIER"));
//    }

//    /**
//     * Test of insert method, of class FavoriteDao.
//     */
//    @Test
//    public void testInsertNotExist() throws Exception {
        // How test this ?
//    }
    
    /**
     * Test of insert method, of class FavoriteDao.
     */
    @Test
    public void testInsertInvalidParameter() throws Exception {
        System.out.println(" Test insert invalid parameter");

        assertThrows(RepositoryException.class, () -> {
            instance.insert(null);
        });
    }

//    /**
//     * Test of update method, of class FavoriteDao.
//     */
//    @Test
//    public void testUpdate() throws Exception {
//        System.out.println("update");
//        FavoriteDto newDto = new FavoriteDto("test", "ROGIER", "MADOU");
//        String oldKey = "ephec";
//        instance.update(newDto, oldKey);
//
//        assertEquals(instance.select(newDto.getKey()),
//                new FavoriteDto("test", "ROGIER", "MADOU"));
//    }

    /**
     * Test of update method, of class FavoriteDao.
     */
    @Test
    public void testUpdateNotExist() throws Exception {
        System.out.println("update not exist");
        FavoriteDto item = new FavoriteDto("notExist", "DELTA", "ROGIER");
        String oldKey = "atomium";
        instance.update(item, oldKey);
        assertNull(instance.select(item.getKey()));

    }

    /**
     * Test of update method, of class FavoriteDao.
     */
    @Test
    public void testUpdateInvalidParam() throws Exception {
        System.out.println("update invalid param");
        assertThrows(RepositoryException.class, () -> {
            instance.update(null, "ecole");
        });
    }

    /**
     * Test of update method, of class FavoriteDao.
     */
    @Test
    public void testUpdateInvalidParam2() throws Exception {
        System.out.println("update invalid param");
        assertThrows(RepositoryException.class, () -> {
            instance.update(school, null);
        });
    }

//    /**
//     * Test of delete method, of class FavoriteDao.
//     */
//    @Test
//    public void testDelete() throws Exception {
//        instance.delete("cinema");
//        assertNull(instance.select("cinema"));
//    }

    /**
     * Test of delete method, of class FavoriteDao.
     */
    @Test
    public void testDeleteNotExist() throws Exception {
        instance.delete("sport");
        assertNull(instance.select("sport"));
    }

    /**
     * Test of delete method, of class FavoriteDao.
     */
    @Test
    public void testDeleteInvalidParam() throws Exception {
        assertThrows(RepositoryException.class, () -> {
            instance.delete(null);
        });
    }
}
