package esi.atl.main;

import esi.atl.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import esi.atl.model.Facade;
import esi.atl.model.Model;
import esi.atl.view.InterfaceView;

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
        Model model = new Facade();

        Controller controller = new Controller(model, view);
        view.setController(controller);
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
