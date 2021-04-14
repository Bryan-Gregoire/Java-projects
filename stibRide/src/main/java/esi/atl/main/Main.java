package esi.atl.main;

import esi.atl.config.ConfigManager;
import esi.atl.model.Facade;
import esi.atl.presenter.Presenter;
import esi.atl.view.View;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        try {
            ConfigManager.getInstance().load();
        } catch (IOException e) {
            System.out.println("Configuration cannot be loaded. "
                    + e.getMessage());
        }
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/fxml/mainView.fxml"));
        Parent root = loader.load();

        Facade model = new Facade();
        View view = new View();
        Presenter presenter = new Presenter(model, view);

        //model.addPropertyChangeListener(presenter);
        //presenter.initialise();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

}
