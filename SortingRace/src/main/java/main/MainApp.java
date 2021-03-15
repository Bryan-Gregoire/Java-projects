package main;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Model;
import view.InterfaceView;
import view.View;

/**
 *
 * @author g53735
 */
public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/fxml/sort.fxml"));
        Parent root = loader.load();
        
        InterfaceView view = loader.getController();
        Model model = new Model();

        Controller controller = new Controller(model, view);
        view.setController(controller);
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
