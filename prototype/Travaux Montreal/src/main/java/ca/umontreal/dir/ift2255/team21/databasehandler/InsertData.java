package ca.umontreal.dir.ift2255.team21.databasehandler;
import java.sql.*;
import java.time.LocalDate;

public class InsertData {

    private static String jdbcUrl = DataForConnection.ENDPOINT.getUrl();
    private static String admin = DataForConnection.USER.getUrl();
    private static String access = DataForConnection.KEY.getUrl();
    public void insertion (String first_name, String last_name, String residential_adress, String electronic_adress,
                           String phone_number, LocalDate date_naissance, String passwordHash, String usernameDB,
                           int cityNumber) {

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            // Charger le driver JDBC MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Établir la connexion
            conn = DriverManager.getConnection(jdbcUrl, admin, access);


            String sqlInsert = "INSERT INTO `Loggin Credentials` (password, email) VALUES (?,?)";
            ps = conn.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            ps.setString(2, usernameDB);
            ps.setString(1, passwordHash);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                int userID = rs.getInt(1);
                sqlInsert = "INSERT INTO `User Information` (`UserID`, " +
                        "FirstName, LastName, ResidentialAddress, ElectronicAddress," +
                        "PhoneNumber, BirthDate, CityNumber) VALUES (?,?,?,?,?,?,?,?)";
                ps = conn.prepareStatement(sqlInsert);
                ps.setInt(1, userID);
                ps.setString(2, first_name);
                ps.setString(3, last_name);
                ps.setString(4, residential_adress);
                ps.setString(5, electronic_adress);
                ps.setString(6, phone_number);
                ps.setDate(7, java.sql.Date.valueOf(date_naissance));
                ps.setInt(8, cityNumber);
                ps.executeUpdate();
            }

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