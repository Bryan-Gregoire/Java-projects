package view;

import controller.Controller;
import java.beans.PropertyChangeEvent;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.input.KeyCombination;

public class View implements Initializable, InterfaceView{

    @FXML
    private MenuItem quitItem;

    @FXML
    private MenuItem aboutItem;

    @FXML
    private TableColumn nameCol;

    @FXML
    private TableColumn sizeCol;

    @FXML
    private TableColumn swapCol;

    @FXML
    private TableColumn durationCol;

    @FXML
    private LineChart chart;

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

    Controller controller;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Start
        LocalDateTime start = LocalDateTime.now();

        // Item quit
        quitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        quitItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                System.exit(0);
            }
        });
        // End Item quit

        // LineChart 
        XYChart.Series bubbleSortSerie = new XYChart.Series();
        bubbleSortSerie.setName("Tri à bulles");
        XYChart.Series mergeSortSerie = new XYChart.Series();
        mergeSortSerie.setName("Tri fusion");
        chart.getData().addAll(bubbleSortSerie, mergeSortSerie);
        // End LineChart

        // End LineChart
        // ChoiceBox types of sorting
        String bubble = "Tri à bulles";
        sortChoice.setValue(bubble);
        sortChoice.getItems().add(bubble);
        sortChoice.getItems().add("Tri Fusion");
        // End ChoiceBox types of sorting

        // Thread Spinner
        final int defaultValue = 1;
        SpinnerValueFactory<Integer> valueFactory
                = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10,
                        defaultValue);
        threadSpinner.setValueFactory(valueFactory);
        // End Thread Spinner

        // ChoiceBox of the level of difficulty
        String veryEasy = "Very Easy : 0 - 100 - 10";
        configurationChoice.setValue(veryEasy);
        configurationChoice.getItems().add(veryEasy);
        configurationChoice.getItems().add("Easy : 0 - 1 000 - 100");
        configurationChoice.getItems().add("Moderate : 0 - 10 000 - 1000");
        configurationChoice.getItems().add("Hard : 0 - 100 000 - 10 000");
        // End ChoiceBox of the level of difficulty

        // Label number of active threads
        leftStatus.setText("Threads actifs : " + Thread.activeCount());
        // End Label number of active threads

        // Label start time, end time and duration in milliseconds
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern(("HH:mm:ss:SSS"));
        rightStatus.setText("Derniere exécution | Début : "
                + start.format(formatter) + " - " + "Fin : "
                + end.format(formatter) + " Durée | Duration : "
                + duration.toMillis() + " ms ");
        // End Label start time, end time and duration in milliseconds
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void propertyChange(PropertyChangeEvent evt) {
        // TODO
    }
}
