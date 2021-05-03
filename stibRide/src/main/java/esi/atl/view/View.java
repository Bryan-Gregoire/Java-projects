package esi.atl.view;

import esi.atl.dto.FavoriteDto;
import esi.atl.model.Node;
import esi.atl.model.StationData;
import esi.atl.presenter.Presenter;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    private MenuItem about;

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
    private TableColumn<FavoriteDto, String> favCol;

    @FXML
    private TableColumn<FavoriteDto, String> originCol;

    @FXML
    private TableColumn<FavoriteDto, String> destCol;

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

    @FXML
    private Label favSolutions;

    private ObservableList<StationData> itineraryDatas;
    private ObservableList<FavoriteDto> favoritesDatas;

    Alert aboutAlert;
    Alert favNameExist;

    public void initialize() {
        initQuitAction();
        initAlertAbout();
        initAboutAction();
        initAlertFavNameExist();
        initStationTableView();
        initFavoriteTableView();
    }

    public void initQuitAction() {
        quitItem.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        quitItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                System.exit(0);
            }
        });
    }

    public void initAlertAbout() {
        aboutAlert = new Alert(Alert.AlertType.INFORMATION);
        aboutAlert.setTitle("Stib");
        aboutAlert.setHeaderText("Search the fastest route");

        aboutAlert.setContentText("To search a route between two stations, "
                + "select a home and destination station and press the search"
                + " button.");
    }

    public void initAboutAction() {
        about.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                aboutAlert.show();
            }
        });
    }

    public void initStationTableView() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        linesCol.setCellValueFactory(new PropertyValueFactory<>("lines"));

        itineraryDatas = FXCollections.observableArrayList();
        itineraryTable.setItems(itineraryDatas);
    }

    public void initFavoriteTableView() {
        favCol.setCellValueFactory(new PropertyValueFactory<>("key"));
        originCol.setCellValueFactory(new PropertyValueFactory<>("origin"));
        destCol.setCellValueFactory(new PropertyValueFactory<>("destination"));

        favoritesDatas = FXCollections.observableArrayList();
        favTable.setItems(favoritesDatas);
    }

    public void addFavTableMouseClicked(Presenter presenter) {
        MouseClickedHandler handle = new MouseClickedHandler(presenter);
        favTable.setOnMouseClicked(handle);
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

    public void hideEmptyStationLbl() {
        this.emptyStation.setVisible(false);
    }

    public void showEmptyStationLbl() {
        this.emptyStation.setVisible(true);
    }

    public int getNbStation() {
        return itineraryDatas.size();
    }

    public boolean selectedStationsIsEmpty() {
        return this.origin.getValue() == null
                || this.destination.getValue() == null;
    }

    public void addIteneraryData(List<StationData> data) {
        itineraryDatas.clear();
        itineraryDatas.addAll(data);
    }

    public void fillSearchableComboBox(List dtos) {
        origin.getItems().addAll(dtos);
        destination.getItems().addAll(dtos);
        origin.setValue(dtos.get(0));
        destination.setValue(dtos.get(dtos.size() - 1));
    }

    public void addSearchHandler(Presenter presenter) {
        SearchHandler handler = new SearchHandler(presenter);
        search.setOnAction(handler);
    }

    public String getFavTextField() {
        return this.favText.getText();
    }

    public void setFavText(String text) {
        this.favText.setText(text);
    }

    public FavoriteDto getSelectedFav() {
        return (FavoriteDto) this.favTable.getSelectionModel()
                .getSelectedItem();
    }

    public boolean isFavTextEmpty() {
        return this.favText.getText().isEmpty();
    }

    public void hideFavSolution() {
        this.favSolutions.setVisible(false);
    }

    public void showFavSolution() {
        this.favSolutions.setVisible(true);
    }

    public ObservableList<FavoriteDto> getAllFavorites() {
        return this.favoritesDatas;
    }

    public void addAllFavToTable(List<FavoriteDto> favorites) {
        this.favoritesDatas.addAll(favorites);
    }

    public void addFavToTable(FavoriteDto dto) {
        this.favoritesDatas.add(dto);
    }

    public void removeFavFromTable(FavoriteDto dto) {
        this.favoritesDatas.remove(dto);
    }

    public void addInsertHandler(Presenter presenter) {
        InsertHandler handler = new InsertHandler(presenter);
        add.setOnAction(handler);
    }

    public void addUpdateHandler(Presenter presenter) {
        UpdateHandler handler = new UpdateHandler(presenter);
        update.setOnAction(handler);
    }

    public void addDeleteHandler(Presenter presenter) {
        DeleteHandler handler = new DeleteHandler(presenter);
        delete.setOnAction(handler);
    }

    public void initAlertFavNameExist() {
        favNameExist = new Alert(Alert.AlertType.ERROR);
        favNameExist.setTitle("Favorites");
        favNameExist.setHeaderText("Favorite already exists");

        favNameExist.setContentText("The name you entered already exists, "
                + "please try with a new name.");
    }

    public void showFavNameExistAlert() {
        favNameExist.show();
    }
}
