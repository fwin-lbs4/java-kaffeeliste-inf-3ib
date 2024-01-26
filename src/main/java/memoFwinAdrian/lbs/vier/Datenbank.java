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
        String url = "jdbc:mysql://localhost:3306/kaffeeliste";
        String username = "root";
        String password = "";
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
                "SELECT us.Name, Sum(sld.Anderung), us.Rolle FROM user us\n" +
                        "JOIN schulden sld ON us.idUser = sld.Anderung\n" +
                        "WHERE us.idUser = ?")) {

            preparedStatement.setInt(1, idUser);
            ResultSet rs = preparedStatement.executeQuery();
            rs.first();
            name = rs.getString("Name");
            rolle = rs.getString("Rolle");
            schulden = rs.getInt("Anderung");
            rs.close();

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
     * @param idUser Die ID des Benutzers.
     * @return Eine Liste von User-Objekten mit den Benutzerinformationen.
     */
    public List<User> getAllUserInfos(int idUser) {
        List<User> users = new ArrayList<>();

        String name = "";
        String rolle = "";
        int schulden = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "Select u.Name, u.Rolle, u.idUser, Sum(s.Anderung) from user u\n" +
                        "JOIN schulden s ON u.idUser = s.User_idUser;")) {

            ResultSet rs = preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    name = rs.getString("Name");
                    rolle = rs.getString("Rolle");
                    schulden = rs.getInt("Schulden");
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
                "Select Preis, Name, idKaffee from kaffee;")) {

            ResultSet rs = preparedStatement.executeQuery();
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    idKaffee = rs.getInt("idKaffee");
                    name = rs.getString("Name");
                    preis = rs.getInt("Preis");
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
                "INSERT INTO schulden\n" +
                        "(`User_idUser,`" +
                        "`Anderung`,\n" +
                        "`Zeitstempel`)\n" +
                        "VALUES\n" +
                        "(?,?,?);")) {
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
