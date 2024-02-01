package memoFwinAdrian.lbs.vier;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

/**
 * Generischer Controller, der gemeinsame Funktionalitäten für verschiedene Ansichten bereitstellt.
 */
public class GenericController {
    /**
     * Die Stage der Applikation.
     */
    protected Stage stage;
    /**
     * Die gemeinsame Scene.
     */
    protected Scene scene;
    /**
     * Der vorherige Root.
     */
    protected Parent previousRoot;
    /**
     * Der nachfolgende Root.
     */
    protected GenericController previousController;
    /**
     * Der nachfolgende Root.
     */
    protected Parent nextRoot;
    /**
     * Der nachfolgende Controller.
     */
    protected GenericController nextController;
    /**
     * Datenbank-Zugang.
     */
    protected Datenbank db;
    /**
     * Der UserHolder.
     */
    protected UserHolder currentUser;
    /**
     * Liste der User.
     */
    protected List<User> userList;

    /**
     * Initialisiert den Controller mit den erforderlichen Ressourcen.
     *
     * @param db                 Datenbank-Objekt
     * @param stage              JavaFX Stage-Objekt
     * @param scene              JavaFX Scene-Objekt
     * @param previousRoot       Vorherige Wurzel (Parent) der Szene
     * @param previousController Vorheriger Controller
     * @param nextRoot           Nächste Wurzel (Parent) der Szene
     * @param nextController     Nächster Controller
     * @param currentUser        Benutzerinformationen
     * @param userList           Liste der Benutzer
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
    protected void init() {
    }

    /**
     * Methode zum Aktualisieren der Benutzeroberfläche.
     * Diese Methode kann von abgeleiteten Klassen überschrieben werden, um spezifische Aktualisierungen vorzunehmen.
     */
    protected void refresh() {
    }
}
