package atl.grade.exercice;

import atl.grade.Demo;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import org.sqlite.SQLiteConfig;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class insertGrades extends Demo {

    @Override
    public void execute(String url) {
        try {

            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            // Connection connexion = DriverManager
            //.getConnection("jdbc:sqlite:" + url);

            Connection connexion = DriverManager.getConnection("jdbc:sqlite:"
                    + url, config.toProperties());

            Statement stmt = connexion.createStatement();

            Date date
                    = new Date(Calendar.getInstance().getTimeInMillis());

            Timestamp modified
                    = (new Timestamp(Calendar.getInstance().getTime()
                            .getTime()));

            String query = "INSERT INTO GRADES(score,date,dateModified,"
                    + "id_student,id_lesson) VALUES(2, '" + date + "'"
                    + ",'" + modified + "', 1, 'SECL')";

            int count = stmt.executeUpdate(query);
            System.out.println("\t Nombre de record modifié : " + count);

            ResultSet result = stmt.getGeneratedKeys();
            while (result.next()) {
                int id = result.getInt(1);
                System.out.println("\t clé ajoutée : " + id);
            }

        } catch (SQLException ex) {
            System.out.println("DEMO_INSERT | Erreur " + ex.getMessage()
                    + " SQLState " + ex.getSQLState());
        }
    }

    @Override
    public String getTitle() {
        return "Insertion dans Grades ";
    }
}
