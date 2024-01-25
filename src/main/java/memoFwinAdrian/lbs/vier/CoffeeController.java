package memoFwinAdrian.lbs.vier;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CoffeeController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}