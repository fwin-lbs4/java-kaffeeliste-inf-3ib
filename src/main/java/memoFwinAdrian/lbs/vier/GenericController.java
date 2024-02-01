package memoFwinAdrian.lbs.vier;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;
/**
 * Generischer Controller, der gemeinsame Funktionalitäten für verschiedene Ansichten bereitstellt.
 */
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

    /**
     * Initialisiert den Controller mit den erforderlichen Ressourcen.
     *
     * @param db                  Datenbank-Objekt
     * @param stage               JavaFX Stage-Objekt
     * @param scene               JavaFX Scene-Objekt
     * @param previousRoot        Vorherige Wurzel (Parent) der Szene
     * @param previousController  Vorheriger Controller
     * @param nextRoot            Nächste Wurzel (Parent) der Szene
     * @param nextController      Nächster Controller
     * @param currentUser         Benutzerinformationen
     * @param userList            Liste der Benutzer
     */
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
    /**
     * Methode zur Initialisierung des Controllers.
     * Diese Methode kann von abgeleiteten Klassen überschrieben werden, um spezifische Initialisierungen vorzunehmen.
     */
    protected void init() {}

    /**
     * Methode zum Aktualisieren der Benutzeroberfläche.
     * Diese Methode kann von abgeleiteten Klassen überschrieben werden, um spezifische Aktualisierungen vorzunehmen.
     */
    protected void refresh() {}
}
