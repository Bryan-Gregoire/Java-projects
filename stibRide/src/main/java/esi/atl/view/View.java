package esi.atl.view;

import esi.atl.model.Node;
import esi.atl.model.StationData;
import esi.atl.presenter.Presenter;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import org.controlsfx.control.SearchableComboBox;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class View {

    @FXML
    private MenuItem quitItem;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblNbStation;

    @FXML
    private SearchableComboBox origin;

    @FXML
    private SearchableComboBox destination;

    @FXML
    private Button search;

    @FXML
    private TableView itineraryTable;

    @FXML
    private TableColumn<StationData, String> nameCol;

    @FXML
    private TableColumn<StationData, List<Node>> linesCol;

    @FXML
    private TableView favTable;

    @FXML
    private TableColumn favCol;

    @FXML
    private TableColumn originCol;

    @FXML
    private TableColumn destCol;

    @FXML
    private TextField favText;

    @FXML
    private Button add;

    @FXML
    private Button update;

    @FXML
    private Button delete;

    @FXML
    private Label emptyStation;

    private ObservableList<StationData> itineraryData;

    public void initialize() {
        initMenu();
        initTableView();
    }

    public String getOrigin() {
        return (String) origin.getValue();
    }

    public String getDestination() {
        return (String) destination.getValue();
    }

    public void setLblStatusText(String text) {
        this.lblStatus.setText(text);
    }

    public void setLblNbStationText(String text) {
        this.lblNbStation.setText(text);
    }

    public void hideEmptyLbl() {
        this.emptyStation.setVisible(false);
    }

    public void showEmptyLbl() {
        this.emptyStation.setVisible(true);
    }

    public int getNbStation() {
        return itineraryData.size();
    }

    public void addIteneraryData(List<StationData> data) {
        itineraryData.clear();
        itineraryData.addAll(data);
    }

    public void initMenu() {
        quitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        quitItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                System.exit(0);
            }
        });
    }

    public void addSearchHandler(Presenter presenter) {
        SearchHandler handler = new SearchHandler(presenter);
        search.setOnAction(handler);
    }

    public void fillSearchableComboBox(List dtos) {
        origin.getItems().addAll(dtos);
        destination.getItems().addAll(dtos);
        origin.setValue(dtos.get(0));
        destination.setValue(dtos.get(dtos.size() - 1));
    }

    public void initTableView() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        linesCol.setCellValueFactory(new PropertyValueFactory<>("lines"));

        itineraryData = FXCollections.observableArrayList();
        itineraryTable.setItems(itineraryData);
    }

}
