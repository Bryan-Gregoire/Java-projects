package esi.atl.view;

import esi.atl.dto.StationDto;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.controlsfx.control.SearchableComboBox;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class View implements Initializable {

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblNbStation;

    @FXML
    private SearchableComboBox<StationDto> listStations;

    @FXML
    private SearchableComboBox<StationDto> listStations2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void fillSearchableComboBox(List<StationDto> dtos) {
        listStations.getItems().addAll(dtos);
        listStations2.getItems().addAll(dtos);
    }

}
