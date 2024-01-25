package memoFwin.lbs4;

import java.sql.*;

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
            schulden = rs.getInt("Schulden");
            rs.close();

            User user = new User(name, schulden, idUser, rolle);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}

