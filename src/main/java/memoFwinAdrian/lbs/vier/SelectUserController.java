package memoFwinAdrian.lbs.vier;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class SelectUserController extends GenericController {
    @FXML
    private Label welcomeText;
    private Label box;
    @FXML
    private ComboBox<User> comboBox;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Select User!");
    }

    @FXML
    protected void onSelectClick() {
        onRefreshButtonClick();
    }

    @FXML
    protected void onRefreshButtonClick() {
        ObservableList<User> list = this.comboBox.getItems();
        list.clear();
        list.addAll(this.userList);
    }

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