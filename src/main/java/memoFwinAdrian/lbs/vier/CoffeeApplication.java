package memoFwinAdrian.lbs.vier;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Klasse welche die JavaFX-Applikation startet.
 */
public class CoffeeApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Datenbank db = new Datenbank();

        UserHolder holder = UserHolder.getInstance();
        holder.setUser(null);

        List<User> userList = db.getAllUserInfos();

        FXMLLoader userLoader = new FXMLLoader(CoffeeApplication.class.getResource("select-user-view.fxml"));
        FXMLLoader coffeeLoader = new FXMLLoader(CoffeeApplication.class.getResource("select-coffee-view.fxml"));
        FXMLLoader adminLoader = new FXMLLoader(CoffeeApplication.class.getResource("admin-view.fxml"));

        Parent userRoot = userLoader.load();
        Parent coffeeRoot = coffeeLoader.load();
        Parent adminRoot = adminLoader.load();

        Scene scene = new Scene(userRoot, 320, 240);

        stage.setTitle("Select User!");
        stage.setScene(scene);

        SelectUserController userController = userLoader.getController();
        SelectCoffeeController coffeeController = coffeeLoader.getController();
        AdminController adminController = adminLoader.getController();

        userController.setup(
                db,
                stage,
                scene,
                null,
                null,
                coffeeRoot,
                coffeeController,
                holder,
                userList
        );

        coffeeController.setup(
                db,
                stage,
                scene,
                userRoot,
                userController,
                adminRoot,
                adminController,
                holder,
                userList
        );

        adminController.setup(
                db,
                stage,
                scene,
                coffeeRoot,
                coffeeController,
                null,
                null,
                holder,
                userList
        );

        stage.show();
    }
}