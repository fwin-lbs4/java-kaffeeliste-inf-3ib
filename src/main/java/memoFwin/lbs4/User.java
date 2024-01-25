package memoFwin.lbs4;

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
}
