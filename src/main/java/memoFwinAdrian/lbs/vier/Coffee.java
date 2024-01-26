package memoFwinAdrian.lbs.vier;

/**
 * Die Klasse Coffee repräsentiert Kaffeeartikeln mit einer eindeutigen ID, einem Namen und einem Preis.
 * Sie stellt Methoden bereit, um die ID, den Namen und den Preis des Kaffees abzurufen und zu setzen.
 */
public class Coffee {

    /**
     * Die eindeutige Kennung für den Kaffee.
     */
    private int idKaffee;

    /**
     * Der Name des Kaffees.
     */
    private String name;

    /**
     * Der Preis des Kaffees.
     */
    private int preis;

    /**
     * Konstruiert ein neues Coffee-Objekt mit der angegebenen ID, dem Namen und dem Preis.
     *
     * @param idKaffee Die eindeutige Kennung für den Kaffee.
     * @param name     Der Name des Kaffees.
     * @param preis    Der Preis des Kaffees.
     */
    public Coffee(int idKaffee, String name, int preis) {
        this.idKaffee = idKaffee;
        this.name = name;
        this.preis = preis;
    }

    /**
     * Gibt die eindeutige Kennung des Kaffees zurück.
     *
     * @return Die eindeutige Kennung des Kaffees.
     */
    public int getIdKaffee() {
        return idKaffee;
    }

    /**
     * Setzt die eindeutige Kennung des Kaffees.
     *
     * @param idKaffee Die zu setzende eindeutige Kennung.
     */
    public void setIdKaffee(int idKaffee) {
        this.idKaffee = idKaffee;
    }

    /**
     * Gibt den Namen des Kaffees zurück.
     *
     * @return Der Name des Kaffees.
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen des Kaffees.
     *
     * @param name Der zu setzende Name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gibt den Preis des Kaffees zurück.
     *
     * @return Der Preis des Kaffees.
     */
    public int getPreis() {
        return preis;
    }

    /**
     * Setzt den Preis des Kaffees.
     *
     * @param preis Der zu setzende Preis.
     */
    public void setPreis(int preis) {
        this.preis = preis;
    }
}

