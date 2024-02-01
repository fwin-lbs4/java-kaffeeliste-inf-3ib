package memoFwinAdrian.lbs.vier;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Controller-Klasse für die Admin-Ansicht in der Anwendung.
 * Erweitert die GenericController-Klasse, um gemeinsame Funktionalitäten zu erben.
 */
public class AdminController extends GenericController {
    /**
     * Field in dem die Gutschrift eingegeben wird.
     */
    @FXML
    private TextField addField;
    /**
     * Field in dem der Pin eingegeben wird.
     */
    @FXML
    private TextField pinField;
    /**
     * Überschrift für den View.
     */
    @FXML
    private Label labelField;
    /**
     * Button um den Login zu starten.
     */
    @FXML
    private Button loginButton;
    /**
     * Button um die Gutschrift einzufügen.
     */
    @FXML
    private Button submitButton;
    /**
     * Combobox in der die User ausgewählt werden können.
     */
    @FXML
    private ComboBox<User> comboBox;

    /**
     * Setzt die UI-Elemente auf ihren ursprünglichen Zustand zurück.
     */
    private void reset() {
        this.submitButton.setVisible(false);
        this.submitButton.setManaged(false);
        this.comboBox.setVisible(false);
        this.comboBox.setManaged(false);
        this.addField.setVisible(false);
        this.addField.setManaged(false);

        this.comboBox.valueProperty().set(null);
        this.labelField.setText("Please enter your pin");
        this.addField.clear();
        this.pinField.clear();

        this.loginButton.setVisible(true);
        this.loginButton.setManaged(true);
        this.pinField.setVisible(true);
        this.pinField.setManaged(true);
    }

    /**
     * Behandelt die Aktion, wenn der Anmelde-Button angeklickt wird.
     * Versucht, den Benutzer basierend auf der eingegebenen PIN anzumelden.
     */
    @FXML
    protected void onLoginButtonClick() {
        try {
            int pin = Integer.parseInt(this.pinField.getText());

            if (this.currentUser.getUser().getPin() == pin) {
                this.loginButton.setVisible(false);
                this.loginButton.setManaged(false);
                this.pinField.setVisible(false);
                this.pinField.setManaged(false);

                this.labelField.setText("Admin");
                this.addField.clear();
                this.pinField.clear();

                ObservableList<User> list = this.comboBox.getItems();
                list.clear();
                list.addAll(this.userList);

                this.submitButton.setVisible(true);
                this.submitButton.setManaged(true);
                this.comboBox.setVisible(true);
                this.comboBox.setManaged(true);

                return;
            }
        } catch (NumberFormatException ignored) {
        }

        this.reset();
    }

    /**
     * Behandelt die Aktion, wenn der Button angeklickt wird.
     * Fügt einen Schulden-Eintrag hinzu und aktualisiert die Benutzeroberfläche.
     */
    @FXML
    protected void onSubmitButtonClick() {
        if (this.comboBox.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        this.db.addSchulden(this.comboBox.getSelectionModel().getSelectedItem(),
                            -1 * Integer.parseInt(this.addField.getText())
        );

        this.onBackButtonClick();
        this.previousController.refresh();
    }

    /**
     * Behandelt die Aktion, wenn der Zurück-Button angeklickt wird.
     * Setzt die UI zurück und navigiert zur vorherigen Ansicht zurück.
     */
    @FXML
    protected void onBackButtonClick() {
        this.reset();
        this.previousController.refresh();
        this.stage.setTitle("Select Coffee!");
        this.scene.setRoot(this.previousRoot);
    }

    /**
     * Behandelt die Aktion, wenn ein Element in der ComboBox ausgewählt wird.
     * Aktualisiert die Benutzeroberfläche basierend auf dem ausgewählten Benutzer.
     */
    @FXML
    protected void onSelectShowing() {
        if (this.comboBox.getSelectionModel().getSelectedItem() == null) {
            this.addField.setVisible(false);
            this.addField.setManaged(false);

            this.labelField.setText("Admin");
            this.submitButton.setDisable(true);

            return;
        }

        this.submitButton.setDisable(false);

        User user = this.comboBox.getSelectionModel().getSelectedItem();
        this.labelField.setText(user.getName() + " - " + user.getSchuldenString());

        this.addField.setVisible(true);
        this.addField.setManaged(true);
    }

    /**
     * Behandelt die Aktion, wenn eine Taste auf der PIN-Tastatur gedrückt wird.
     * Wenn die Enter-Taste gedrückt wird, wird die Anmeldeaktion ausgelöst.
     */
    @FXML
    protected void onPinKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            this.onLoginButtonClick();
        }
    }
}
