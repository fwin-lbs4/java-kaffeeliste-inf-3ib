package memoFwinAdrian.lbs.vier;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class GenericController {
    protected Stage stage;
    protected Scene scene;
    protected Parent previousRoot;
    protected Parent nextRoot;
    protected Datenbank db;

    protected User currentUser;

    protected List<User> userList;


    public void setup(Datenbank db, Stage stage, Scene scene, Parent previousRoot, Parent nextRoot, User currentUser, List<User> userList) {
        this.db = db;
        this.stage = stage;
        this.scene = scene;
        this.previousRoot = previousRoot;
        this.nextRoot = nextRoot;
        this.currentUser = currentUser;
        this.userList = userList;
    }
}
