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
public class SelectAllLessons extends Demo {

    @Override
    public void execute(String url) {
        try {
            Connection connexion = DriverManager.getConnection("jdbc:sqlite:"
                    + url);
            Statement stmt = connexion.createStatement();

            String query = "SELECT acronym FROM LESSONS";

            ResultSet result = stmt.executeQuery(query);

            while (result.next()) {
                String acronym = result.getString(1);
                System.out.println("\t record : " + acronym);
            }

        } catch (SQLException ex) {
            System.out.println("SELECT_ALL_LESSONS | Erreur " + ex.getMessage()
                    + " SQLState " + ex.getSQLState());
        }
    }

    @Override
    public String getTitle() {
        return "La liste des utilisateurs en DB";
    }
}
