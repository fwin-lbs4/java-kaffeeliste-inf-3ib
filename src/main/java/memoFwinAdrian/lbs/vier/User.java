package memoFwinAdrian.lbs.vier;

public class User {
    private String name;
    private int idUser;
    private int schulden;
    private String rolle;
    private int pin;

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
        int schuldenValue = this.schulden;

        String schuldenType = "";
        if (schuldenValue >= 0) {
            schuldenType = "Schulden";
        } else {
            schuldenType = "Guthaben";
            schuldenValue = schuldenValue * -1;
        }

        String schuldenValueString = Integer.toString(schuldenValue);

        if (schuldenValueString.length() < 3) {
            schuldenValueString = "0".repeat(3 - schuldenValueString.length()) + schuldenValueString;
        }

        String cents = schuldenValueString.substring(schuldenValueString.length() - 2);
        String euro = schuldenValueString.substring(0, schuldenValueString.length() - 2);

        schuldenValueString = "€ " + euro + "," + (cents.equals("00") ? "--" : cents);

        return schuldenType + ": " + schuldenValueString;
    }
}
