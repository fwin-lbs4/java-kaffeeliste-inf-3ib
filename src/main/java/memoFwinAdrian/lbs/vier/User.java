package memoFwinAdrian.lbs.vier;

public class User {
    private final String name;
    private final int idUser;
    private final String rolle;
    private final int pin;
    private int schulden;

    public User(String name, int schulden, int idUser, String rolle, int pin) {
        this.name = name;
        this.schulden = schulden;
        this.idUser = idUser;
        this.rolle = rolle;
        this.pin = pin;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getName() {
        return name;
    }

    public int getSchulden() {
        return schulden;
    }

    public void setSchulden(int schulden) {
        this.schulden = schulden;
    }

    public String getRolle() {
        return rolle;
    }

    public int getPin() {
        return this.pin;
    }

    public String toString() {
        return this.getName();
    }

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
