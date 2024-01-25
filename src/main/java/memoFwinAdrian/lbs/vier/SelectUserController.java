package memoFwinAdrian.lbs.vier;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

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