package memoFwinAdrian.lbs.vier;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;


public class CoffeeApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Datenbank db = new Datenbank();

        User currentUser = null;
        List<User> userList = db.getAllUserInfos();

        FXMLLoader userLoader = new FXMLLoader(CoffeeApplication.class.getResource("select-user-view.fxml"));
        FXMLLoader coffeeLoader = new FXMLLoader(CoffeeApplication.class.getResource("select-coffee-view.fxml"));
        Parent userRoot = userLoader.load();
        Parent coffeeRoot = coffeeLoader.load();

        Scene scene = new Scene(userRoot, 320, 240);
        stage.setTitle("Select User!");
        stage.setScene(scene);

        SelectUserController userController = userLoader.getController();
        SelectCoffeeController coffeeController = coffeeLoader.getController();

        userController.setup(db, stage, scene, null, coffeeRoot, currentUser, userList);
        coffeeController.setup(db, stage, scene, userRoot, null, currentUser, userList);

        stage.show();
    }
}