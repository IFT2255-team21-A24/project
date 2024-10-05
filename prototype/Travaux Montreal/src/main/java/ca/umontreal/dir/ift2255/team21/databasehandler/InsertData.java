package ca.umontreal.dir.ift2255.team21.databasehandler;
import java.sql.*;

public class InsertData {

    private static String jdbcUrl = "jdbc:mysql://ift2255.cfm0oqm68j83.us-east-2.rds.amazonaws.com:3306/DataUser?useSSL=false&serverTimezone=UTC";
    private static String admin = "admin";
    private static String access = "";
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
