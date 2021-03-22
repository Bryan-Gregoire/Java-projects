package atl.grade.exercice;

import atl.grade.Demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class ManageLessons extends Demo {

    @Override
    public void execute(String url) {
        try {
            Connection connexion = DriverManager.getConnection("jdbc:sqlite:"
                    + url);
            Statement stmt = connexion.createStatement();

            System.out.println("INSERT ANLL:");

            String queryInsert = "INSERT INTO LESSONS(acronym) values('ANLL')";

            int countInsert = stmt.executeUpdate(queryInsert);
            System.out.println("\t Nombre de record modifié : " + countInsert);

            ResultSet resultInsert = stmt.getGeneratedKeys();

            while (resultInsert.next()) {
                int id = resultInsert.getInt(1);
                System.out.println("\t clé ajoutée : " + id);
            }

            System.out.println("");

            System.out.println("Select ALL : ");

            String querySelectAll = "SELECT acronym FROM LESSONS";
            ResultSet resultSelectAll = stmt.executeQuery(querySelectAll);

            while (resultSelectAll.next()) {
                String acronym = resultSelectAll.getString(1);
                System.out.println("\t record : " + acronym);
            }

            System.out.println("");

            System.out.println("DELETE ANLL : ");

            String queryDelete = "DELETE FROM LESSONS WHERE acronym ='ANLL'";

            int countDelete = stmt.executeUpdate(queryDelete);
            System.out.println("\t Nombre de record modifié : " + countDelete);

            System.out.println("");

            System.out.println("SELECT ALL 2 : ");

            String querySelectAll2 = "SELECT acronym FROM LESSONS";
            ResultSet resultSelectAll2 = stmt.executeQuery(querySelectAll2);

            while (resultSelectAll.next()) {
                String acronym = resultSelectAll2.getString(1);
                System.out.println("\t record : " + acronym);
            }
            System.out.println("");

        } catch (SQLException ex) {
            System.out.println("MANAGE_LESSONS| Erreur " + ex.getMessage()
                    + " SQLState " + ex.getSQLState());
        }
    }

    @Override
    public String getTitle() {
        return "ANLL";
    }
}
