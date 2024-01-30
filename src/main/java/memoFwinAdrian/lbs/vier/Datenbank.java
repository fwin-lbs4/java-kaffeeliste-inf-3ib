package memoFwinAdrian.lbs.vier;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Die Datenbank-Klasse ermöglicht die Verbindung und Interaktion mit der Datenbank
 * für die Verwaltung von Benutzerinformationen und Schulden.
 */
public class Datenbank {

    private final Connection connection;

    /**
     * Konstruktor für die Datenbank-Klasse.
     * <p>
     * Die URL für die Datenbankverbindung.
     * Der Benutzername für die Datenbankverbindung.
     * Das Passwort für die Datenbankverbindung.
     */
    public Datenbank() {
        String url = "jdbc:mysql://localhost/kaffeeliste";
        String username = "root";
        String password = null;
        try {
            this.connection = DriverManager.getConnection(url, username, password);
            System.out.println("Verbindung hergestellt");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Verbindungsherstellung nicht möglich", e);
        }
    }

    /**
     * Ruft die Informationen eines Benutzers anhand seiner ID aus der Datenbank ab.
     *
     * @param idUser Die ID des Benutzers.
     * @return Ein Objekt der Klasse User mit den Benutzerinformationen.
     */
    public User getUserInfos(int idUser) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
       """
                SELECT
                    u.Name as Name,
                    u.Pin as Pin,
                    COALESCE(SUM(s.Anderung), 0) AS Schulden,
                    u.Rolle as Rolle
                FROM
                    user AS u
                    LEFT JOIN schulden AS s ON u.idUser = s.User_idUser
                WHERE u.idUser = ?
                GROUP BY u.idUser
                LIMIT 1
           """
        )) {

            preparedStatement.setInt(1, idUser);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    throw new SQLException("No Rows found");
                }
                String name = resultSet.getString("Name");
                int pin = resultSet.getInt("Pin");

                String rolle = resultSet.getString("Rolle");
                int schulden = resultSet.getInt("Schulden");

                return new User(name, schulden, idUser, rolle, pin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Ruft die Informationen aller Benutzer aus der Datenbank ab.
     *
     * @return Eine Liste von User-Objekten mit den Benutzerinformationen.
     */
    public List<User> getAllUserInfos() {
        List<User> users = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
       """
               SELECT
                   u.Name AS Name,
                   u.Rolle AS Rolle,
                   u.idUser AS idUser,
                   u.pin AS Pin,
                   COALESCE(SUM(s.Anderung), 0) AS Schulden
               FROM
                   user AS u
                   LEFT JOIN schulden AS s ON u.idUser = s.User_idUser
               GROUP BY u.idUser;
           """
        )) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String name = resultSet.getString("Name");
                    String rolle = resultSet.getString("Rolle");
                    int schulden = resultSet.getInt("Schulden");
                    int idUser = resultSet.getInt("idUser");
                    int pin = resultSet.getInt("Pin");
                    User user = new User(name, schulden, idUser, rolle, pin);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    /**
     * Ruft die Informationen aller Kaffees aus der Datenbank ab.
     *
     * @return Eine Liste von Caffee-Objekten mit der Kaffelisten Information.
     */
    public List<Coffee> getAllCoffees() {
        List<Coffee> coffees = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(
        """
                SELECT
                    Preis AS Preis,
                    Name AS Name,
                    idKaffee AS idKaffee
                FROM kaffee;
            """
        )) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int idKaffee = resultSet.getInt("idKaffee");
                    String name = resultSet.getString("Name");
                    int preis = resultSet.getInt("Preis");
                    Coffee coffee = new Coffee(idKaffee, name, preis);
                    coffees.add(coffee);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coffees;
    }

    /**
     * Fügt Schulden für einen Benutzer in die Datenbank ein.
     *
     * @param user     Das Benutzerobjekt, für das Schulden hinzugefügt werden sollen.
     * @param schulden Der Betrag der hinzuzufügenden Schulden.
     * @return True wenn Schulden hinzufügen erfolgreich war sonst False.
     */
    public Boolean addSchulden(User user, int schulden) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
       """
                INSERT INTO schulden
                    (`User_idUser`, `Anderung`, `Zeitstempel`)
                VALUES
                    (?, ?, ?);
           """
        )) {
            preparedStatement.setInt(1, user.getIdUser());
            preparedStatement.setInt(2, schulden);
            preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));
            preparedStatement.executeUpdate();

            user.setSchulden(user.getSchulden() + schulden);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
