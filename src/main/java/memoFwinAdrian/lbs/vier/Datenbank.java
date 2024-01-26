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

    private Connection connection;

    /**
     * Konstruktor für die Datenbank-Klasse.
     *
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
        String name = "";
        String rolle = "";
        int schulden = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                """
                        SELECT us.Name as Name, Sum(sld.Anderung) as Schulden, us.Rolle as Rolle FROM user us
                        JOIN schulden sld ON us.idUser = sld.Anderung
                        WHERE us.idUser = ? 
                    """)) {

            preparedStatement.setInt(1, idUser);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    name = resultSet.getString("Name");
                    rolle = resultSet.getString("Rolle");
                    schulden = resultSet.getInt("Schulden");
                }
            }

            User user = new User(name, schulden, idUser, rolle);
            return user;
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
                            COALESCE(SUM(s.Anderung), 0) AS Schulden
                        FROM user AS u
                        LEFT JOIN schulden AS s ON u.idUser = s.User_idUser
                        GROUP BY u.idUser;
                    """)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String name = resultSet.getString("Name");
                    String rolle = resultSet.getString("Rolle");
                    int schulden = resultSet.getInt("Schulden");
                    int idUser = resultSet.getInt("idUser");
                    User user = new User(name, schulden, idUser, rolle);
                    users.add(user);
                }
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Ruft die Informationen aller Kaffees aus der Datenbank ab.
     *
     * @return Eine Liste von Caffee-Objekten mit der Kaffelisten Information.
     */
    public List<Coffee> getAllCoffees() {
        List<Coffee> coffees = new ArrayList<>();

        String name = "";
        int preis = 0;
        int idKaffee = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "Select Preis as Preis, Name as Name, idKaffee as idKaffee from kaffee;")) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    idKaffee = resultSet.getInt("idKaffee");
                    name = resultSet.getString("Name");
                    preis = resultSet.getInt("Preis");
                    Coffee coffee = new Coffee(idKaffee, name, preis);
                    coffees.add(coffee);
                }
            }
            return coffees;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Fügt Schulden für einen Benutzer in die Datenbank ein.
     *
     * @param user     Das Benutzerobjekt, für das Schulden hinzugefügt werden sollen.
     * @param schulden Der Betrag der hinzuzufügenden Schulden.
     * @return Das aktualisierte Benutzerobjekt mit den neuen Schuldeninformationen.
     */
    public User addSchulden(User user, int schulden) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                """
                        INSERT INTO schulden
                        (`User_idUser,``Anderung`,
                        `Zeitstempel`)
                        VALUES
                        (?,?,?);
                        """
                )) {
            preparedStatement.setInt(1, user.getIdUser());
            preparedStatement.setInt(2, schulden);
            preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));
            user.setSchulden(user.getSchulden() + schulden);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
