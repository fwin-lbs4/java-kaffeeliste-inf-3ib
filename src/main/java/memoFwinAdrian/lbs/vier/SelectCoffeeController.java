package memoFwinAdrian.lbs.vier;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SelectCoffeeController extends GenericController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Select Coffee!");
    }

    @FXML
    protected void onBackButtonClick() {
        this.stage.setTitle("Select User!");
        this.scene.setRoot(this.previousRoot);
    }
}
