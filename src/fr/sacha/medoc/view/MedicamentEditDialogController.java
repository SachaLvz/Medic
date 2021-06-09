package fr.sacha.medoc.view;

import fr.sacha.medoc.model.Medicament;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Dialog to edit details of a person.
 * 
 * @author Marco Jakob
 */
public class MedicamentEditDialogController {

    @FXML
    private TextField name;
    @FXML
    private TextField details;
    @FXML
    private TextField quantity;


    private Stage dialogStage;
    private Medicament medicament;
    private boolean okClicked = false;


    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMedicament(Medicament medicament) {

        this.medicament = medicament;

        name.setText(medicament.getName());
        details.setText(medicament.getDetails());
        quantity.setText(String.valueOf(medicament.getQuantity()));
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            medicament.setName(name.getText());
            medicament.setDetails(details.getText());
            medicament.setQuantity(Integer.valueOf(quantity.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (name.getText() == null || name.getText().length() == 0) {
            errorMessage += "Nom pas valide\n";
        }
        if (details.getText() == null || details.getText().length() == 0) {
            errorMessage += "Détails pas valide!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Champs invalides");
            alert.setHeaderText("Champs invalides");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
}