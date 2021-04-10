package esi.atl.main;

import esi.atl.model.Facade;
import esi.atl.presenter.Presenter;
import esi.atl.view.View;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Parent root
                = FXMLLoader.load(getClass()
                        .getResource("/fxml/mainView.fxml"));

        Facade model = new Facade();
        View view = new View();
        Presenter presenter = new Presenter(model, view);

        //model.addPropertyChangeListener(presenter);
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

    }

}
