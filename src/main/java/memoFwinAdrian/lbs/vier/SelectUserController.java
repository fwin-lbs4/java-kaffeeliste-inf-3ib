package memoFwinAdrian.lbs.vier;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SelectUserController extends GenericController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Select User!");
    }

    @FXML
    protected void onNextButtonClick() {
        this.stage.setTitle("Select Coffee!");
        this.scene.setRoot(this.nextRoot);
    }
}