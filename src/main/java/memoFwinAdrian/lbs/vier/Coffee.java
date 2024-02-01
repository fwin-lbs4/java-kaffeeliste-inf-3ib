package memoFwinAdrian.lbs.vier;

/**
 * Die Klasse Coffee repräsentiert Kaffeeartikeln mit einer eindeutigen ID, einem Namen und einem Preis.
 * Sie stellt Methoden bereit, um die ID, den Namen und den Preis des Kaffees abzurufen und zu setzen.
 *
 * @param idKaffee Die eindeutige Kennung für den Kaffee.
 * @param name     Der Name des Kaffees.
 * @param preis    Der Preis des Kaffees.
 */
public record Coffee(int idKaffee, String name, int preis) {
    /**
     * Gibt die eindeutige Kennung des Kaffees zurück.
     *
     * @return Die eindeutige Kennung des Kaffees.
     */
    @Override
    public int idKaffee() {
        return idKaffee;
    }

    /**
     * Gibt den Namen des Kaffees zurück.
     *
     * @return Der Name des Kaffees.
     */
    @Override
    public String name() {
        return name;
    }

    /**
     * Gibt den Preis des Kaffees zurück.
     *
     * @return Der Preis des Kaffees.
     */
    @Override
    public int preis() {
        return preis;
    }

    /**
     * Gibt eine aufbereitete Representation des Preises zurück.
     *
     * @return Aufbereitete Representation des Preises.
     */
    private String getPreisString() {
        String preis = Integer.toString(this.preis);
        if (preis.length() < 3) {
            preis = "0".repeat(3 - preis.length()) + preis;
        }
        String cents = preis.substring(preis.length() - 2);
        String euro = preis.substring(0, preis.length() - 2);

        return "€ " + euro + "," + (cents.equals("00") ? "--" : cents);
    }

    /**
     * Gibt eine aufbereitete Representation des Kaffees zurück.
     *
     * @return Aufbereitete Representation des Kaffees.
     */
    public String toString() {
        return this.name() + " " + this.getPreisString();
    }
}

