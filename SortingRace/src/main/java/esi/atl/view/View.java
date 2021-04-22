package esi.atl.view;

import esi.atl.model.ArrayData;
import esi.atl.model.Level;
import esi.atl.controller.Controller;
import esi.atl.model.Facade;
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
    private double progress = 0.0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LocalDateTime startTime = LocalDateTime.now();

        initMenuQuit();

        initTableView();

        initLineChart();

        initChoiceBox();

        initSpinner();

        initLevelChoiceBox();

        startButtonSortAction();

        leftStatus.setText("Threads actifs : " + Thread.activeCount());

        timeToLaunch(startTime);
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> { // on passe les instruction de mise à jour au thread jvafx qui fera la mise à jour 
            leftStatus.setText("Threads actifs : " + Thread.activeCount());
        });

        if (evt.getPropertyName().equals(Facade.ARRAY_SORT)) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {

                    dataArray.add((ArrayData) evt.getNewValue());
                    if (sortChoice.getValue().equals(SortType.BUBBLE)) {
                        bubbleSortSerie.getData().add(new XYChart.Data<>(
                                ((ArrayData) evt.getNewValue()).getSize(),
                                ((ArrayData) evt.getNewValue())
                                        .getNbOperationsSort()));
                    } else {
                        mergeSortSerie.getData().add(new XYChart.Data<>(
                                ((ArrayData) evt.getNewValue()).getSize(),
                                ((ArrayData) evt.getNewValue())
                                        .getNbOperationsSort()));
                    }

                    progress = progress + 0.09090909091; // bof :-) 
                    progressBar.setProgress(progress);
                    if (progress >= 1) {
                        disableBottomElements(false);
                        progress = 0;
                    }
                }
            });

        }
    }

    private void initMenuQuit() {
        quitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        quitItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                System.exit(0);
            }
        });
    }

    private void initTableView() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
        swapCol.setCellValueFactory(
                new PropertyValueFactory<>("nbOperationsSort"));
        durationCol.setCellValueFactory(
                new PropertyValueFactory<>("timeToSort"));
        dataArray = FXCollections.observableArrayList();
        table.setItems(dataArray);
    }

    private void initLineChart() {
        bubbleSortSerie = new XYChart.Series();
        bubbleSortSerie.setName("Tri à bulles");
        mergeSortSerie = new XYChart.Series();
        mergeSortSerie.setName("Tri fusion");
        chart.getData().addAll(bubbleSortSerie, mergeSortSerie);
    }

    private void initChoiceBox() {
        sortChoice.setValue(SortType.BUBBLE);
        sortChoice.getItems().addAll(SortType.values());
    }

    private void initSpinner() {
        int spinnerDefaultValue = 1;
        SpinnerValueFactory<Integer> valueFactory
                = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10,
                        spinnerDefaultValue);
        threadSpinner.setValueFactory(valueFactory);
    }

    private void initLevelChoiceBox() {
        configurationChoice.setValue(Level.VERY_EASY);
        configurationChoice.getItems().addAll(Level.values());
    }

    private void startButtonSortAction() {
        this.start.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                progressBar.setProgress(progress);
                disableBottomElements(true);
                int value = (int) threadSpinner.getValue();
                int size = configurationChoice.getValue().getLevel();
                SortType sort = (SortType) sortChoice.getValue();
                if (sort == SortType.BUBBLE) {
                    bubbleSortSerie.getData().clear();
                } else {
                    mergeSortSerie.getData().clear();
                }

                controller.sortNbArrays(value, size, sort);
            }
        });
    }

    private void timeToLaunch(LocalDateTime start) {
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern(("HH:mm:ss:SSS"));
        rightStatus.setText("Derniere exécution | Début : "
                + start.format(formatter) + " - " + "Fin : "
                + end.format(formatter) + " Durée | Duration : "
                + duration.toMillis() + " ms ");
    }

    private void disableBottomElements(boolean disable) {
        start.setDisable(disable);
        sortChoice.setDisable(disable);
        threadSpinner.setDisable(disable);
        configurationChoice.setDisable(disable);
    }

}
