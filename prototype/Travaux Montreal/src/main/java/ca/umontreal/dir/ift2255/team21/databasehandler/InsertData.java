package ca.umontreal.dir.ift2255.team21.databasehandler;
import ca.umontreal.dir.ift2255.team21.entraves.Entraves;
import ca.umontreal.dir.ift2255.team21.entraves.Travaux;
import ca.umontreal.dir.ift2255.team21.requests.Requests;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class InsertData {

    private static String jdbcUrl = DataForConnection.ENDPOINT.getUrl();
    private static String admin = DataForConnection.USER.getUrl();
    private static String access = DataForConnection.KEY.getUrl();
    public static void insertionUtilisateur(String first_name, String last_name, String residential_adress, String electronic_adress,
                           String phone_number, LocalDate date_naissance, String passwordHash,
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
            ps.setString(2, electronic_adress);
            ps.setString(1, passwordHash);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                int userID = rs.getInt(1);
                sqlInsert = "INSERT INTO `User Information` (`User_ID`, " +
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

    public static void insertRequest(Requests request) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcUrl, admin, access);
            String sqlInsert = "INSERT INTO `WorkRequests` (`WorkTitle`, `WorkType`, `Address`,`StartDate`," +
                    "`longitude`, `latitude`, `Status`) VALUES (?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sqlInsert);
            ps.setString(1,request.getName());
            ps.setString(2, request.getType());
            ps.setString(3, request.getAddress());
            ps.setDate(4, request.getBeginDate());
            ps.setDouble(5, request.getLongitude());
            ps.setDouble(6, request.getLatitude());
            ps.setString(7, request.getStatus());
            ps.executeUpdate();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    // PERMET DE GÉNÉRER DES INFOS
    /**
    public static void main(String[] args) {
        // Génération d'un jeu de données
        for (int i = 0; i < 10; i++) {
            String firstName = generateFirstName();
            String lastName = generateLastName();
            String residentialAddress = generateResidentialAddressMontreal();
            String electronicAddress = generateEmail(firstName, lastName);
            String phoneNumber = generatePhoneNumber();
            LocalDate dateNaissance = generateDateNaissance();
            String password = generatePassword();
            int cityNumber = generateCityNumber();
            if (i%2==0){
                cityNumber = -1;
            }
            String passwordHash = PasswordHash.hashPassword(password);
            insertionUtilisateur(firstName, lastName, residentialAddress, electronicAddress, phoneNumber, dateNaissance,
                    passwordHash, cityNumber);
            System.out.println(password);
        }
    }

    private static String generateFirstName() {
        String[] firstNames = {"Alice", "Bob", "Charlie", "Diana", "Eve", "Frank", "Gabriel", "Hélène"};
        return firstNames[new Random().nextInt(firstNames.length)];
    }

    private static String generateLastName() {
        String[] lastNames = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Miller", "Tremblay", "Lefebvre"};
        return lastNames[new Random().nextInt(lastNames.length)];
    }

    private static String generateResidentialAddressMontreal() {
        String[] streets = {
                "Rue Sainte-Catherine", "Avenue du Mont-Royal", "Boulevard Saint-Laurent",
                "Rue Sherbrooke", "Rue Saint-Denis", "Rue Jarry", "Rue Jean-Talon", "Rue Ontario"
        };
        int houseNumber = new Random().nextInt(999) + 1;
        String borough = new Random().nextBoolean() ? "Montréal" : "Montréal-Nord";
        return houseNumber + " " + streets[new Random().nextInt(streets.length)] + ", " + borough + ", QC";
    }

    private static String generateEmail(String firstName, String lastName) {
        String[] domains = {"example.com", "mail.com", "email.ca", "test.org"};
        return firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + domains[new Random().nextInt(domains.length)];
    }

    private static String generatePhoneNumber() {
        Random rand = new Random();
        return String.format("(514) %03d-%04d", rand.nextInt(1000), rand.nextInt(10000));
    }


    private static LocalDate generateDateNaissance() {
        Random rand = new Random();
        int year = rand.nextInt(30) + 1970; // Entre 1970 et 1999
        int month = rand.nextInt(12) + 1;
        int day = rand.nextInt(28) + 1; // Simplification : max 28 jours
        return LocalDate.of(year, month, day);
    }

    private static String generatePassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 10; i++) { // Longueur de mot de passe : 10 caractères
            password.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return password.toString();
    }

    private static int generateCityNumber() {
        return new Random().nextInt(1000) + 1; // Numéro de ville entre 1 et 1000
    }
**/

}