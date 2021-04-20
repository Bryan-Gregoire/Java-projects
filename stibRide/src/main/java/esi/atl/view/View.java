package esi.atl.view;

import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.controlsfx.control.SearchableComboBox;

/**
 *
 * @author Bryan Grégoire <53735@etu.he2b.be>
 */
public class View {

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblNbStation;

    @FXML
    private SearchableComboBox origin;

    @FXML
    private SearchableComboBox destination;

    public void setLblStatusText(String text) {
        this.lblStatus.setText(text);
    }

    public void setLblNbStationText(String text) {
        this.lblNbStation.setText(text);

    }

    public void fillSearchableComboBox(List dtos) {
        origin.getItems().addAll(dtos);
        destination.getItems().addAll(dtos);
        origin.setValue(dtos.get(0));
        destination.setValue(dtos.get(dtos.size() - 1));
    }

}
