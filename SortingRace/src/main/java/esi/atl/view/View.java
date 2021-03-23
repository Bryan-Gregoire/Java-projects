package esi.atl.view;

import esi.atl.model.ArrayData;
import esi.atl.model.Level;
import esi.atl.controller.Controller;
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
import esi.atl.model.MyThreads;
import esi.atl.model.SortType;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class View implements Initializable, InterfaceView {

    @FXML
    private MenuItem quitItem;

    @FXML
    private MenuItem aboutItem;

    @FXML
    private TableView<ArrayData> table;

    @FXML
    private TableColumn<ArrayData, String> nameCol;

    @FXML
    private TableColumn<ArrayData, Integer> sizeCol;

    @FXML
    private TableColumn<ArrayData, Long> swapCol;

    @FXML
    private TableColumn<ArrayData, Long> durationCol;

    @FXML
    private LineChart chart;

    @FXML
    private ChoiceBox sortChoice;

    @FXML
    private Spinner threadSpinner;

    @FXML
    private ChoiceBox<Level> configurationChoice;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button start;

    @FXML
    private Label leftStatus;

    @FXML
    private Label rightStatus;

    private Controller controller;

    XYChart.Series bubbleSortSerie;
    XYChart.Series mergeSortSerie;

    ObservableList<ArrayData> dataArray;

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

        // TableView
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
        swapCol.setCellValueFactory(
                new PropertyValueFactory<>("nbOperationsSort"));
        durationCol.setCellValueFactory(
                new PropertyValueFactory<>("timeToSort"));
        dataArray = FXCollections.observableArrayList();
        table.setItems(dataArray);

        // TableView end
        // LineChart 
        bubbleSortSerie = new XYChart.Series();
        bubbleSortSerie.setName("Tri à bulles");
        mergeSortSerie = new XYChart.Series();
        mergeSortSerie.setName("Tri fusion");
        chart.getData().addAll(bubbleSortSerie, mergeSortSerie);
        // End LineChart

        // ChoiceBox types of sorting
        sortChoice.setValue(SortType.BUBBLE);
        sortChoice.getItems().addAll(SortType.values());
        // End ChoiceBox types of sorting

        // Thread Spinner
        final int spinnerDefaultValue = 1;
        SpinnerValueFactory<Integer> valueFactory
                = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10,
                        spinnerDefaultValue);
        threadSpinner.setValueFactory(valueFactory);
        // End Thread Spinner

        // ChoiceBox of the level of difficulty
        configurationChoice.setValue(Level.VERY_EASY);
        configurationChoice.getItems().addAll(Level.values());
        // End ChoiceBox of the level of difficulty

        // Start Button
        this.start.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                int value = (int) threadSpinner.getValue();
                int size = configurationChoice.getValue().getLevel();
                SortType sort = (SortType) sortChoice.getValue();

                controller.sortNbArrays(value, size, sort);
            }
        });
        // End Start button

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

        if (evt.getPropertyName().equals(MyThreads.ARRAY_SORT)) {
            Platform.runLater(() -> { // Laisser priorité au thread du modèle.
                dataArray.add((ArrayData) evt.getNewValue());
            });
            if (sortChoice.getValue().equals(SortType.BUBBLE)) {
                Platform.runLater(() -> {
                    bubbleSortSerie.getData().add(new XYChart.Data<>(
                            ((ArrayData) evt.getNewValue()).getSize(),
                            ((ArrayData) evt.getNewValue())
                                    .getNbOperationsSort()));
                });
            } else {
                Platform.runLater(() -> {
                    mergeSortSerie.getData().add(new XYChart.Data<>(
                            ((ArrayData) evt.getNewValue()).getSize(),
                            ((ArrayData) evt.getNewValue())
                                    .getNbOperationsSort()));
                });
            }
        }
    }
}
