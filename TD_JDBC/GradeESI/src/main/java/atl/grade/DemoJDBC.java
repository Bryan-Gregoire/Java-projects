package atl.grade;

import atl.grade.config.ConfigManager;
import atl.grade.prepare.DemoPrepare;
import java.io.IOException;
import java.util.Scanner;

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

            Scanner input = new Scanner(System.in);
            System.out.println("Id : ");
            int id = input.nextInt();
            input.nextLine();
            System.out.println("Lastname : ");
            String lastname = input.nextLine();

            Demo demo = new DemoPrepare(dbUrl, id, lastname);

            demo.printTitle();
            demo.execute(dbUrl);
        } catch (IOException ex) {
            System.out.println("Erreur IO " + ex.getMessage());
        }
    }

    private DemoJDBC() {
    }
}
