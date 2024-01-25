package memoFwinAdrian.lbs.vier;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GenericController {
    protected Stage stage;
    protected Scene scene;
    protected Parent previousRoot;
    protected Parent nextRoot;
    protected Datenbank db;

    public void setup(Datenbank db, Stage stage, Scene scene, Parent previousRoot, Parent nextRoot) {
        this.db = db;
        this.stage = stage;
        this.scene = scene;
        this.previousRoot = previousRoot;
        this.nextRoot = nextRoot;
    }
}
