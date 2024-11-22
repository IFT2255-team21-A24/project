package ca.umontreal.dir.ift2255.team21.databasehandler;
import ca.umontreal.dir.ift2255.team21.entraves.Entraves;
import ca.umontreal.dir.ift2255.team21.entraves.Travaux;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class InsertData {

    private static String jdbcUrl = DataForConnection.ENDPOINT.getUrl();
    private static String admin = DataForConnection.USER.getUrl();
    private static String access = DataForConnection.KEY.getUrl();
    public static void insertionUtilisateur(String first_name, String last_name, String residential_adress, String electronic_adress,
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
    public static void insertionEntraves(ArrayList<Entraves> entraves) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcUrl, admin, access);
            String sqlInsert = "INSERT INTO `Entraves` (`idEntraves`, `streetImpactType`, `latitude`, `longitude`, " +
                    "`streetname`, `neighberhood`) VALUES (?,?,?,?,?,?)";
            ps = conn.prepareStatement(sqlInsert);
            for (Entraves entrave : entraves) {
                ps.setString(1, entrave.getId_request());
                ps.setString(2, entrave.getStreetimpact());
                ps.setDouble(3, entrave.getLatitude());
                ps.setDouble(4, entrave.getLongitude());
                ps.setString(5, entrave.getSteetid());
                ps.setString(6, entrave.getneighborhood());
                ps.executeUpdate();
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void insertionTravaux(ArrayList<Travaux> travaux) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcUrl, admin, access);
            String sqlInsert = "INSERT INTO `Travaux` (`idTravaux`, `longitude`, `latitude`, `dateDebut`, " +
                    "`dateFin`, `arrondissement`, `currentStatus`, `reasonCategory`, `submitcategory`," +
                    "`organisationName`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sqlInsert);
            for (Travaux travaux1 : travaux) {
                ps.setString(1,travaux1.getId());
                ps.setDouble(2, travaux1.getLongitude());
                ps.setDouble(3, travaux1.getLatitude());
                ps.setDate(4, travaux1.getDateDebut());
                ps.setDate(5, travaux1.getDateFin());
                ps.setString(6, travaux1.getBoroughid());
                ps.setString(7, travaux1.getCurrentstatus());
                ps.setString(8, travaux1.getReason_category());
                ps.setString(9, travaux1.getSubmittercategory());
                ps.setString(10, travaux1.getOrganisationname());
                ps.executeUpdate();
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}