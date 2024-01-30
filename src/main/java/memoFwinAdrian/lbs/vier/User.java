package memoFwinAdrian.lbs.vier;

public class User {
    private String name;
    private int idUser;
    private int schulden;
    private String rolle;
    private int pin;

    public User(String name, int schulden, int idUser, String rolle, int pin){
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
    public String getRolle() {
        return rolle;
    }

    public void setSchulden(int schulden) {
        this.schulden = schulden;
    }
    public int getPin() {
        return this.pin;
    }
    public String toString() {
        return this.getName();
    }

    public String getSchuldenString() {
        String schulden = Integer.toString(this.schulden);
        if (schulden.length() < 3) {
            schulden = "0".repeat(3 - schulden.length()) + schulden;
        }
        String cents = schulden.substring(schulden.length() - 2);
        String euro = schulden.substring(0, schulden.length() - 2);

        return "€ " + euro + "," + (cents.equals("00") ? "--" : cents);
    }
}
