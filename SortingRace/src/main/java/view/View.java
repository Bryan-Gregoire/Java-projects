package view;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class View implements Initializable {

    @FXML
    private ChoiceBox sortChoice;

    @FXML
    private Spinner threadSpinner;

    @FXML
    private ChoiceBox configurationChoice;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button start;

    @FXML
    private Label leftStatus;

    @FXML
    private Label rightStatus;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sortChoice.getItems().add("BubbleSort");
        sortChoice.getItems().add("Tri Fusion");
        threadSpinner.setValueFactory(new SpinnerValueFactory
                .IntegerSpinnerValueFactory(1, 10));

        configurationChoice.getItems().add("Very Easy : 100");
        configurationChoice.getItems().add("Easy: 1_000");
        configurationChoice.getItems().add("Medium : 10_000");
        configurationChoice.getItems().add("Hard : 100_000");

        leftStatus.setText("Threads actifs : " + Thread.activeCount());
        rightStatus.setText("Derniere exécution| Début : "
                + new SimpleDateFormat("HH:mm:ss.SSS")
                        .format(new Date()) + " | Fin - Durée : Duration");

    }

}
