package memoFwinAdrian.lbs.vier;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CoffeeApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        User currentUser = null;
        List<User> userList = new ArrayList<>();
        userList.add(new User("Flo", 500, 1, "User"));
        userList.add(new User("Adrian", 250, 2, "User"));
        userList.add(new User("Memo", 50000, 3, "User"));

//        Datenbank db = new Datenbank();
        FXMLLoader userLoader = new FXMLLoader(CoffeeApplication.class.getResource("select-user-view.fxml"));
        FXMLLoader coffeeLoader = new FXMLLoader(CoffeeApplication.class.getResource("select-coffee-view.fxml"));
        Parent userRoot = userLoader.load();
        Parent coffeeRoot = coffeeLoader.load();

        Scene scene = new Scene(userRoot, 320, 240);
        stage.setTitle("Select User!");
        stage.setScene(scene);

        SelectUserController userController = userLoader.getController();
        SelectCoffeeController coffeeController = coffeeLoader.getController();

        userController.setup(null, stage, scene, null, coffeeRoot, currentUser, userList);
        coffeeController.setup(null, stage, scene, userRoot, null, currentUser, userList);

        stage.show();
    }
}