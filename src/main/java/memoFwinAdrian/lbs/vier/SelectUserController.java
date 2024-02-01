package memoFwinAdrian.lbs.vier;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

/**
 * Controller-Klasse für die Benutzerauswahl-Ansicht.
 * Erweitert die GenericController-Klasse für gemeinsame Funktionalitäten.
 */
public class SelectUserController extends GenericController {
    /**
     * Die comboBox in der die User ausgewählt werden.
     */
    @FXML
    private ComboBox<User> comboBox;

    /**
     * Behandelt die Aktion, wenn auf "Select" geklickt wird.
     * Ruft die Aktualisierung der Benutzeroberfläche auf.
     */
    @FXML
    protected void onSelectClick() {
        onRefreshButtonClick();
    }

    /**
     * Aktualisiert die Benutzeroberfläche und lädt die Benutzerliste in die ComboBox.
     */
    @FXML
    protected void onRefreshButtonClick() {
        ObservableList<User> list = this.comboBox.getItems();
        list.clear();
        list.addAll(this.userList);
    }

    /**
     * Behandelt die Aktion, wenn der "Next" Button angeklickt wird.
     * Setzt den ausgewählten Benutzer, navigiert zur nächsten Ansicht und aktualisiert diese.
     */
    @FXML
    protected void onNextButtonClick() {
        this.currentUser.setUser(this.comboBox.getSelectionModel().getSelectedItem());

        if (this.currentUser.getUser() == null) {
            return;
        }

        this.stage.setTitle("Select Coffee!");
        this.scene.setRoot(this.nextRoot);
        this.nextController.refresh();
    }
}