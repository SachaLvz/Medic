package fr.sacha.medoc.view;

import fr.sacha.medoc.model.Medicament;
import fr.sacha.medoc.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MedicamentOverviewController {
    @FXML
    private TableView<Medicament> personTable;
    @FXML
    private TableColumn<Medicament, String> nameColumn;

    @FXML
    private Label name;
    @FXML
    private Label details;
    @FXML
    private Label quantity;


    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MedicamentOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        
        // Clear person details.
        showMedicamentDetails(null);

        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showMedicamentDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getMedicaments());
    }
    
    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     * 
     * @param medicament the person or null
     */
    private void showMedicamentDetails(Medicament medicament) {
        if (medicament != null) {
            // Fill the labels with info from the person object.
            name.setText(medicament.getName());
            details.setText(medicament.getDetails());
            quantity.setText(String.valueOf(medicament.getQuantity()));
        } else {
            // Person is null, remove all the text.
            name.setText("");
            details.setText("");
        }
    }
    
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteMedicament() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Aucune selection");
            alert.setHeaderText("Aucun medicament selectionné");
            alert.setContentText("Selectionnez un medicament.");
            
            alert.showAndWait();
        }
    }
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewMedicament() {
        Medicament tempMedic = new Medicament();
        boolean okClicked = mainApp.showEditMedicament(tempMedic);
        if (okClicked) {
            mainApp.getMedicaments().add(tempMedic);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditMedicament() {
        Medicament selectedMedicament = personTable.getSelectionModel().getSelectedItem();
        if (selectedMedicament != null) {
            boolean okClicked = mainApp.showEditMedicament(selectedMedicament);
            if (okClicked) {
                showMedicamentDetails(selectedMedicament);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Aucune selection");
            alert.setHeaderText("Aucun medicament selectionné");
            alert.setContentText("Selectionnez un medicament.");
            
            alert.showAndWait();
        }
    }
}