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
    private SearchableComboBox origin;

    @FXML
    private SearchableComboBox destination;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setLblStatusText(String text) {
        this.lblStatus.setText(text);
    }

    public void setLblNbStationText(String text) {
        this.lblNbStation.setText(text);

    }

    public String getOrigin() {
        return origin.getValue().toString();
    }

    public String getDestination() {
        return destination.getValue().toString();
    }

    public void fillSearchableComboBox(List<StationDto> dtos) {
        for (StationDto dto : dtos) {
            String station = dto.getName();
            origin.getItems().add(station);
            destination.getItems().add(station);
        }
        origin.setValue(dtos.get(0).getName());
        destination.setValue(dtos.get(dtos.size() - 1).getName());
    }

}
