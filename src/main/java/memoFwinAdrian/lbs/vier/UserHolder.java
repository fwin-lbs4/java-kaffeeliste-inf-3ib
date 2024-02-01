package memoFwinAdrian.lbs.vier;

/**
 * Klasse, um den aktuellen User zu halten und mit den Views zu synchronisieren.
 */
public final class UserHolder {
    /**
     * Die interne Instanz des Userholders.
     */
    private final static UserHolder INSTANCE = new UserHolder();
    /**
     * Der aktuelle User.
     */
    private User user;

    private UserHolder() {
    }

    /**
     * Getter für die globale Instanz.
     *
     * @return Die globale Instanz des Userholders.
     */
    public static UserHolder getInstance() {
        return INSTANCE;
    }

    /**
     * Getter für den User.
     *
     * @return User
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Setter für den User.
     *
     * @param user der neue User.
     */
    public void setUser(User user) {
        this.user = user;
    }
}
