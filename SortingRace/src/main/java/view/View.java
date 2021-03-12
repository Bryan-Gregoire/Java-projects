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
import model.MyThreads;

public class View implements Initializable, InterfaceView {

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
    private ChoiceBox<String> configurationChoice;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button start;

    @FXML
    private Label leftStatus;

    @FXML
    private Label rightStatus;

    private Controller controller;
    private final String veryEasy = "Very Easy : 0 - 100 - 10";
    private final String easy = "Easy : 0 - 1 000 - 100";
    private final String moderate = "Moderate : 0 - 10 000 - 1000";
    private final String hard = "Hard : 0 - 100 000 - 10 000";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Start
        LocalDateTime startTime = LocalDateTime.now();

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
        configurationChoice.setValue(veryEasy);
        configurationChoice.getItems().add(veryEasy);
        configurationChoice.getItems().add(easy);
        configurationChoice.getItems().add(moderate);
        configurationChoice.getItems().add(hard);
        // End ChoiceBox of the level of difficulty

        // Start Button
        this.start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                int value = (int) threadSpinner.getValue();
                int size = sizeChoice();
                controller.sortNbArrays(value, size);
            }
        });

        // Label number of active threads
        leftStatus.setText("Threads actifs : " + Thread.activeCount());
        // End Label number of active threads

        // Label start time, end time and duration in milliseconds
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(startTime, end);
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern(("HH:mm:ss:SSS"));
        rightStatus.setText("Derniere exécution | Début : "
                + startTime.format(formatter) + " - " + "Fin : "
                + end.format(formatter) + " Durée | Duration : "
                + duration.toMillis() + " ms ");
        // End Label start time, end time and duration in milliseconds
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.equals(MyThreads.ARRAY_SORT)) {
            nameCol.getColumns().add("Tri Fusion");
            int[] evtValue = (int[]) evt.getNewValue();
            sizeCol.getColumns().add(evtValue.length);
        }

        if (evt.equals(MyThreads.OPERATIONS)) {
            swapCol.getColumns().add(evt.getNewValue());
        }

        if (evt.equals(MyThreads.MILLI_SECOND)) {
            durationCol.getColumns().add(evt.getNewValue());
        }
    }

    private int sizeChoice() {
        switch (configurationChoice.getValue()) {
            case veryEasy:
                return 100;
            case easy:
                return 1_000;
            case moderate:
                return 10_000;
            case hard:
                return 100_000;
        }
        return 100;
    }
}
