package memoFwinAdrian.lbs.vier;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class Datenbank {

    private Connection connection;

    /**
     * Konstruktor für die DatabaseConnector-Klasse.
     *
     * @param url      Die URL für die Datenbankverbindung.
     * @param username Der Benutzername für die Datenbankverbindung.
     * @param password Das Passwort für die Datenbankverbindung.
     */
    public Datenbank(String url, String username, String password) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
            System.out.println("Verbindung hergestellt");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Verbindungsherstellung nicht möglich", e);
        }
    }

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

    public List<User> getAllUserInfos(int idUser) {
        List<User> users = new ArrayList<User>();

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

