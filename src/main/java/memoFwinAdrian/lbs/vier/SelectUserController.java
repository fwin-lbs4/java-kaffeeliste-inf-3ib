package memoFwinAdrian.lbs.vier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionModel;

import java.util.ArrayList;

public class SelectUserController extends GenericController {
    @FXML
    private Label welcomeText;
    private Label box;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Select User!");
    }

    @FXML
    private ChoiceBox choiceBox;
    @FXML
    protected void onSelectClick(){
        onRefreshButtonClick();
    }

    @FXML
    protected void onRefreshButtonClick() {
        ObservableList<User> list = this.choiceBox.getItems();
        list.clear();
        list.addAll(this.userList);
    }
    @FXML
    protected void onNextButtonClick() {
        this.currentUser.setUser((User) this.choiceBox.getSelectionModel().getSelectedItem());
        System.out.println(this.currentUser.getUser() == null ? "Null" : this.currentUser.getUser().getName());
        if (this.currentUser.getUser() == null){
            return;
        }


        this.stage.setTitle("Select Coffee!");
        this.scene.setRoot(this.nextRoot);

    }


}