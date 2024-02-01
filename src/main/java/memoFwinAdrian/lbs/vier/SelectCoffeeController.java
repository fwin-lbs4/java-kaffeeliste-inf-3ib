package memoFwinAdrian.lbs.vier;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Controller-Klasse für die Ansicht zur Auswahl von Kaffee.
 * Erweitert die GenericController-Klasse für gemeinsame Funktionalitäten.
 */
public class SelectCoffeeController extends GenericController {
    /**
     * Liste der Kaffees.
     */
    private final List<Coffee> coffeeList = new ArrayList<>();
    /**
     * Der admin-button.
     */
    @FXML
    private Button adminButton;
    /**
     * Label das den ausgewählten Usernamen beinhaltet.
     */
    @FXML
    private Label selectedUserNameLabel;
    /**
     * Label das die Schulden des User beinhaltet.
     */
    @FXML
    private Label selectedUserSchuldenLabel;
    /**
     * Der ausgewählte Kaffee.
     */
    private Coffee selectedCoffee;
    /**
     * Vbox welche die Liste der Kaffees hält.
     */
    @FXML
    private VBox coffeeBox;
    /**
     * Der submit button.
     */
    @FXML
    private Button submitButton;

    /**
     * Aktualisiert die Kaffeeauswahl in der Benutzeroberfläche.
     */
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

            coffeeButton.setStyle(this.selectedCoffee != null && this.selectedCoffee.idKaffee() == coffee.idKaffee() ? """
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

    /**
     * Aktualisiert die Benutzeroberfläche und zeigt Benutzerinformationen und den Admin-Button an.
     */
    public void refresh() {
        if (this.currentUser.getUser() == null) {
            this.onBackButtonClick();
        }

        this.selectedUserNameLabel.setText("User: " + this.currentUser.getUser().getName());

        this.selectedUserSchuldenLabel.setText(this.currentUser.getUser().getSchuldenString());

        int schulden = this.currentUser.getUser().getSchulden();

        Color bgColor = new Color(0, 0, 0, 0);

        if (schulden < 0) {
            bgColor = new Color(0, 1, 0, 0.25);
        }

        if (schulden > 0) {
            bgColor = new Color(1, 0, 0, 0.25);
        }

        this.selectedUserSchuldenLabel.setBackground(new Background(new BackgroundFill(bgColor,
                                                                                       new CornerRadii(0),
                                                                                       new Insets(0)
        )));

        if (Objects.equals(this.currentUser.getUser().getRolle(), "Administrator")) {
            this.adminButton.setVisible(true);
        }
    }

    /**
     * Behandelt die Aktion, wenn der Admin-Button angeklickt wird.
     * Navigiert zur Admin-Ansicht.
     */
    @FXML
    protected void onAdminButtonClick() {
        this.stage.setTitle("Admin");
        this.scene.setRoot(this.nextRoot);
        this.nextController.refresh();
    }

    /**
     * Behandelt die Aktion, wenn der Übermitteln-Button angeklickt wird.
     * Fügt Schulden für den ausgewählten Kaffee hinzu und kehrt zur vorherigen Ansicht zurück.
     */
    @FXML
    protected void onSubmitButtonClick() {
        if (this.currentUser.getUser() != null && this.selectedCoffee != null) {
            this.db.addSchulden(this.currentUser.getUser(), this.selectedCoffee.preis());
        }
        this.onBackButtonClick();
    }

    /**
     * Behandelt die Aktion, wenn der Zurück-Button angeklickt wird.
     * Setzt die ausgewählte Kaffeeinformation zurück und navigiert zur vorherigen Ansicht zurück.
     */
    @FXML
    protected void onBackButtonClick() {
        this.selectedCoffee = null;
        this.stage.setTitle("Select User!");
        this.scene.setRoot(this.previousRoot);
    }

    /**
     * Überschreibt die init-Methode der GenericController-Klasse und ruft die Aktualisierung der Kaffeeauswahl auf.
     */
    @Override
    public void init() {
        this.onRefreshButtonClick();
    }
}
