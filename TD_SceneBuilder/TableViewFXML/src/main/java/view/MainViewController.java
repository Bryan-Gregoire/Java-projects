package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainViewController implements Initializable {

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
    void addEtudiant(ActionEvent event) {
        int nr = Integer.parseInt(matricule.getText());
        String pnm = firstName.getText();
        String lstName = LastName.getText();
        tableView.getItems().add(new Etudiant(nr, pnm, lstName));
        matricule.clear();
        firstName.clear();
        LastName.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        num.setCellValueFactory(new PropertyValueFactory<>("num"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));

        tableView.setItems(fullTableView());
    }

    public ObservableList<Etudiant> fullTableView() {
        ObservableList<Etudiant> etudiants = FXCollections.observableArrayList();
        etudiants.add(new Etudiant(1, "Arthur", "Paquot"));
        etudiants.add(new Etudiant(53735, "Bryan", "Grégoire"));
        etudiants.add(new Etudiant(54637, "Billal", "Zidi"));
        return etudiants;
    }
}
