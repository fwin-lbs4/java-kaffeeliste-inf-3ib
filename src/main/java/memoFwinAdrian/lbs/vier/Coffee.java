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
     * Gibt den Namen des Kaffees zurück.
     *
     * @return Der Name des Kaffees.
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt den Preis des Kaffees zurück.
     *
     * @return Der Preis des Kaffees.
     */
    public int getPreis() {
        return preis;
    }

    private String getPreisString() {
        String cents = Integer.toString(this.preis);
        cents = cents.substring(cents.length() - 2);
        String euro = Integer.toString(this.preis);
        euro = euro.substring(0, euro.length() - 2);

        return "€ " + euro + "," + (cents.equals("00") ? "--" : cents);
    }

    public String toString() {
        return this.getName() + " " + this.getPreisString();
    }

}

