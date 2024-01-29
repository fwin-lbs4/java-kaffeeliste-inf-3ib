package memoFwinAdrian.lbs.vier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

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
        ObservableList<String> list = this.choiceBox.getItems();
        list.clear();
        this.userList.forEach((user)->{
            list.add(user.getName());
        });
    }
    @FXML
    protected void onNextButtonClick() {
        this.stage.setTitle("Select Coffee!");
        this.scene.setRoot(this.nextRoot);
    }


}