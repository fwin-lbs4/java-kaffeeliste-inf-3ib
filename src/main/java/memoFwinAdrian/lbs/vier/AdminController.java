package memoFwinAdrian.lbs.vier;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class AdminController extends GenericController {
    @FXML
    private TextField addField;
    @FXML
    private TextField pinField;
    @FXML
    private Label labelField;
    @FXML
    private Button loginButton;
    @FXML
    private Button submitButton;
    @FXML
    private ComboBox<User> comboBox;

    private void reset() {
        this.loginButton.setVisible(true);
        this.submitButton.setVisible(false);
        this.pinField.setVisible(true);
        this.comboBox.setVisible(false);
        this.comboBox.valueProperty().set(null);
        this.labelField.setText("Please enter your pin");
        this.addField.clear();
        this.addField.setVisible(false);
        this.pinField.clear();
    }

    @FXML
    protected void onLoginButtonClick() {
        try {
            int pin = Integer.parseInt(this.pinField.getText());

            if (this.currentUser.getUser().getPin() == pin) {
                this.loginButton.setVisible(false);
                this.submitButton.setVisible(true);
                this.pinField.setVisible(false);
                this.comboBox.setVisible(true);
                this.labelField.setText("Admin");
                this.addField.clear();
                this.pinField.clear();

                ObservableList<User> list = this.comboBox.getItems();
                list.clear();
                list.addAll(this.userList);
                return;
            }
        } catch (NumberFormatException ignored) {
        }

        this.reset();
    }

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

    @FXML
    protected void onBackButtonClick() {
        this.reset();
        this.previousController.refresh();
        this.stage.setTitle("Select Coffee!");
        this.scene.setRoot(this.previousRoot);
    }

    @FXML
    protected void onSelectShowing() {
        if (this.comboBox.getSelectionModel().getSelectedItem() == null) {
            this.submitButton.setDisable(true);
            this.addField.setVisible(false);
            this.labelField.setText("Admin");
            return;
        }

        this.submitButton.setDisable(false);
        this.addField.setVisible(true);
        User user = this.comboBox.getSelectionModel().getSelectedItem();
        this.labelField.setText(user.getName() + " - " + user.getSchuldenString());
    }

    @FXML
    protected void onPinKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            this.onLoginButtonClick();
        }
    }
}
