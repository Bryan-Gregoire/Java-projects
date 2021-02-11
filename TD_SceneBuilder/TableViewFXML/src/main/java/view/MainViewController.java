package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainViewController implements Initializable {

    private ObservableList<Etudiant> listTable;

    @FXML
    private TableView tableView;

    @FXML
    private TableColumn<Etudiant, Integer> num;

    @FXML
    private TableColumn<Etudiant, String> prenom;

    @FXML
    private TableColumn<Etudiant, String> nom;

    @FXML
    private TextField matricule;

    @FXML
    private TextField firstName;

    @FXML
    private TextField LastName;

    @FXML
    private void clearMat() {
        matricule.clear();
        matricule.setStyle("-fx-text-fill: black;");
    }

    @FXML
    void addEtudiant(ActionEvent event) {
        try {
            int nr = Integer.parseInt(matricule.getText());
            String pnm = firstName.getText();
            String lstName = LastName.getText();
            listTable.add(new Etudiant(nr, pnm, lstName));
            matricule.clear();
            firstName.clear();
            LastName.clear();
        } catch (NumberFormatException e) {
            matricule.setText("Ce n'est pas un nombre!");
            matricule.setStyle("-fx-text-fill: red;");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listTable = FXCollections.observableArrayList();
        listTable.add(new Etudiant(53735, "Bryan", "Grégoire"));
        
        num.setCellValueFactory(new PropertyValueFactory<>("num"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));

        tableView.setItems(listTable);
    }
}
