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
    public String toString() {
        return this.getName();
    }
}
