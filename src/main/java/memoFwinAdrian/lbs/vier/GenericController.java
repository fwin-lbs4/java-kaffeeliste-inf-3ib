package memoFwinAdrian.lbs.vier;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class GenericController {
    protected Stage stage;
    protected Scene scene;
    protected Parent previousRoot;
    protected GenericController previousController;
    protected Parent nextRoot;
    protected GenericController nextController;
    protected Datenbank db;
    protected UserHolder currentUser;
    protected List<User> userList;

    public void setup(
            Datenbank db,
            Stage stage,
            Scene scene,
            Parent previousRoot,
            GenericController previousController,
            Parent nextRoot,
            GenericController nextController,
            UserHolder currentUser,
            List<User> userList
    ) {
        this.db = db;
        this.stage = stage;
        this.scene = scene;
        this.previousRoot = previousRoot;
        this.previousController = previousController;
        this.nextRoot = nextRoot;
        this.nextController = nextController;
        this.currentUser = currentUser;
        this.userList = userList;

        this.init();
    }

    protected void init() {}
    protected void refresh() {}
}
