package memoFwinAdrian.lbs.vier;

public class User {
    private String name;
    private int idUser;
    private int schulden;
    private String rolle;

    public User(String name, int schulden, int idUser, String rolle){
        this.name = name;
        this.schulden = schulden;
        this.idUser = idUser;
        this.rolle = rolle;
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
    public String toString() {
        return this.getName();
    }

    public String getSchuldenString() {
        String cents = Integer.toString(this.schulden);
        cents = cents.substring(cents.length() - 2);
        String euro = Integer.toString(this.schulden);
        euro = euro.substring(0, euro.length() - 2);

        return "€ " + euro + "," + (cents.equals("00") ? "--" : cents);
    }
}
