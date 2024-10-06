package ca.umontreal.dir.ift2255.team21.databasehandler;
import java.sql.*;

public class InsertData {

    private static String jdbcUrl = DataForConnection.ENDPOINT.getUrl();
    private static String admin = DataForConnection.USER.getUrl();
    private static String access = DataForConnection.KEY.getUrl();
    public void insertion (String table, String usernameDB, String passwordHash) {

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // Charger le driver JDBC MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Établir la connexion
            conn = DriverManager.getConnection(jdbcUrl, admin, access);

            String sqlInsert = "INSERT INTO `" + table + "` (password, username) VALUES (?,?)";

            ps = conn.prepareStatement(sqlInsert);

            ps.setString(2, usernameDB);
            ps.setString(1, passwordHash);

            ps.executeUpdate();

            // Si la connexion est réussie
            if (conn != null) {
                System.out.println("Connexion à la base de données MySQL réussie !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void insertion (String[] table, String first_name, String last_name, String residential_adress, String electronic_adress,
                            int[] phone_number, int id, Date date_naissance, int city_indentification, String passwordHash) {

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // Charger le driver JDBC MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Établir la connexion
            conn = DriverManager.getConnection(jdbcUrl, admin, access);

            // Si la connexion est réussie
            if (conn != null) {
                System.out.println("Connexion à la base de données MySQL réussie !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
