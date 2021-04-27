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
    private Label emptyFavText;

    private ObservableList<StationData> itineraryDatas;
    private ObservableList<FavoriteDto> favoritesDatas;

    public void initialize() {
        initMenu();
        initStationTableView();
        initFavoriteTableView();
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

    public String getFavText() {
        return this.favText.getText();
    }

    public boolean isFavTextEmpty() {
        return this.favText.getText().isEmpty();
    }

    public void hideEmptyFavLbl() {
        this.emptyFavText.setVisible(false);
    }

    public void showEmptyFavLbl() {
        this.emptyFavText.setVisible(true);
    }

    public void addSearchHandler(Presenter presenter) {
        SearchHandler handler = new SearchHandler(presenter);
        search.setOnAction(handler);
    }

    public void addInsertHandler(Presenter presenter) {
        InsertHandler handler = new InsertHandler(presenter);
        add.setOnAction(handler);
        update.setOnAction(handler);
    }

    public void addDeleteHandler(Presenter presenter) {
        DeleteHandler handler = new DeleteHandler(presenter);
        delete.setOnAction(handler);
    }

    public ObservableList<FavoriteDto> getAllFavorites() {
        return this.favoritesDatas;
    }

    public void addFavToTable(FavoriteDto dto) {
        this.favoritesDatas.add(dto);
    }

    public void removeFavFromTable(FavoriteDto dto) {
        this.favoritesDatas.remove(dto);
    }

//    public boolean containFav(FavoriteDto fav) {
//        return this.favoritesDatas.contains(fav);
//    }
}
