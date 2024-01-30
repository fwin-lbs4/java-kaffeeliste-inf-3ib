package memoFwinAdrian.lbs.vier;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SelectCoffeeController extends GenericController {
    private final List<Coffee> coffeeList = new ArrayList<>();
    @FXML
    private Button adminButton;
    @FXML
    private Label selectedUserLabel;
    private Coffee selectedCoffee;
    @FXML
    private VBox coffeeBox;
    @FXML
    private Button submitButton;

    @FXML
    protected void onRefreshButtonClick() {
        if (this.currentUser == null) {
            this.onBackButtonClick();
        }

        this.submitButton.setDisable(this.selectedCoffee == null);
        ObservableList<Node> children = this.coffeeBox.getChildren();
        children.clear();
        this.coffeeList.clear();
        this.coffeeList.addAll(this.db.getAllCoffees());

        this.coffeeList.forEach((coffee) -> {
            Button coffeeButton = new Button();
            coffeeButton.setText(coffee.toString());
            coffeeButton.setMaxWidth(Double.POSITIVE_INFINITY);

            coffeeButton.setStyle(this.selectedCoffee != null && this.selectedCoffee.getIdKaffee() == coffee.getIdKaffee() ? """
                        -fx-background-color: rgb(0,255,0,0.25);
                        -fx-border-color: rgb(0, 255, 255);
                    """ : "");

            coffeeButton.setOnAction((button) -> {
                this.selectedCoffee = coffee;
                this.onRefreshButtonClick();
            });

            children.add(coffeeButton);
        });
    }

    public void refresh() {
        if (this.currentUser.getUser() == null) {
            this.onBackButtonClick();
        }

        this.selectedUserLabel.setText("User \"" + this.currentUser
                .getUser()
                .getName() + "\" Schulden: \"" + this.currentUser.getUser().getSchuldenString() + "\"");

        if (Objects.equals(this.currentUser.getUser().getRolle(), "Administrator")) {
            this.adminButton.setVisible(true);
        }
    }

    @FXML
    protected void onAdminButtonClick() {
        this.stage.setTitle("Admin");
        this.scene.setRoot(this.nextRoot);
        this.nextController.refresh();
    }

    @FXML
    protected void onSubmitButtonClick() {
        if (this.currentUser.getUser() != null && this.selectedCoffee != null) {
            this.db.addSchulden(this.currentUser.getUser(), this.selectedCoffee.getPreis());
        }
        this.onBackButtonClick();
    }

    @FXML
    protected void onBackButtonClick() {
        this.selectedCoffee = null;
        this.stage.setTitle("Select User!");
        this.scene.setRoot(this.previousRoot);
    }

    @Override
    public void init() {
        this.onRefreshButtonClick();
    }
}
