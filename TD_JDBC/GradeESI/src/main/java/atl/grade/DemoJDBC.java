package atl.grade;

import atl.grade.config.ConfigManager;
import atl.grade.exercice.insertGrades;
import java.io.IOException;

/**
 *
 * @author jlc
 */
public class DemoJDBC {

    /**
     * Entry points to the <code> Mentoring </code> application.
     *
     * @param args no arguments needed.
     */
    public static void main(String[] args) {
        try {
            ConfigManager.getInstance().load();
            String dbUrl = ConfigManager.getInstance().getProperties("db.url");
            System.out.println("Base de données stockée : " + dbUrl);

            Demo demo = new insertGrades();
            demo.printTitle();
            demo.execute(dbUrl);
        } catch (IOException ex) {
            System.out.println("Erreur IO " + ex.getMessage());
        }
    }

    private DemoJDBC() { }
}
