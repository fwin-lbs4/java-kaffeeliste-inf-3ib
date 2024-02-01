package memoFwinAdrian.lbs.vier;

/**
 * User-Klasse repräsentiert einen User.
 * Der User hat einen Namen, eine eindeutige ID, eine Rolle, eventuell einen Pin und Schulden/Guthaben.
 * Sie stellt diverse Getter/Setter und Hilfs-Methoden bereit.
 */
public class User {
    /**
     * Name des Users.
     */
    private final String name;
    /**
     * Eindeutige ID des Users.
     */
    private final int idUser;
    /**
     * Die Rolle des Users.
     */
    private final String rolle;
    /**
     * Der Pin des Users.
     */
    private final int pin;
    /**
     * Der Schulden/Guthaben-Stand des Users.
     */
    private int schulden;

    /**
     * Konstruiert ein neues User-Objekt mit dem gegebenen Namen, den Schulden, der ID, der Rolle und dem Pin.
     *
     * @param name     Name des Users.
     * @param schulden Schulden/Guthaben des Users.
     * @param idUser   ID des Users.
     * @param rolle    Rolle des Users.
     * @param pin      Pin des Users.
     */
    public User(String name, int schulden, int idUser, String rolle, int pin) {
        this.name = name;
        this.schulden = schulden;
        this.idUser = idUser;
        this.rolle = rolle;
        this.pin = pin;
    }

    /**
     * Gibt ID des Users zurück.
     *
     * @return ID des Users.
     */
    public int getIdUser() {
        return idUser;
    }

    /**
     * Gibt Namen des Users zurück.
     *
     * @return Namen des Users.
     */
    public String getName() {
        return name;
    }

    /**
     * Gibt Schulden-Stand des Users zurück.
     *
     * @return Schulden-Stand des Users.
     */
    public int getSchulden() {
        return schulden;
    }

    /**
     * Setzt den Schulden-Stand des Users.
     *
     * @param schulden neuer Schulden-Stand des Users.
     */
    public void setSchulden(int schulden) {
        this.schulden = schulden;
    }

    /**
     * Gibt die Rolle des Users zurück.
     *
     * @return Rolle des Users.
     */
    public String getRolle() {
        return rolle;
    }

    /**
     * Gibt den Pin des Users zurück.
     *
     * @return Pin des Users.
     */
    public int getPin() {
        return this.pin;
    }

    /**
     * Gibt den Namen des Users zurück.
     *
     * @return Namen des Users.
     */
    public String toString() {
        return this.getName();
    }

    /**
     * Gibt eine aufbereitete Representation der Schulden/Guthaben zurück.
     *
     * @return Aufbereitete Representation der Schulden/Guthaben
     */
    public String getSchuldenString() {
        String schuldenValue = Integer.toString(this.schulden >= 0 ? this.schulden : this.schulden * -1);
        String schuldenType = this.schulden >= 0 ? "Schulden" : "Guthaben";

        if (schuldenValue.length() < 3) {
            schuldenValue = "0".repeat(3 - schuldenValue.length()) + schuldenValue;
        }

        String cents = schuldenValue.substring(schuldenValue.length() - 2);
        String euro = schuldenValue.substring(0, schuldenValue.length() - 2);

        schuldenValue = "€ " + euro + "," + (cents.equals("00") ? "--" : cents);

        return schuldenType + ": " + schuldenValue;
    }
}
