package memoFwinAdrian.lbs.vier;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
