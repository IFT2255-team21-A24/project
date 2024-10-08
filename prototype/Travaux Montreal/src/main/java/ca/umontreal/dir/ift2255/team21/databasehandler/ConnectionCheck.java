package ca.umontreal.dir.ift2255.team21.databasehandler;
import ca.umontreal.dir.ift2255.team21.accounts.Account;
import ca.umontreal.dir.ift2255.team21.accounts.Manager;
import ca.umontreal.dir.ift2255.team21.accounts.Resident;

import java.sql.*;
public class ConnectionCheck {
    public static Account checkForUser(String access, String hash) {
        Account user = null;
        // Informations de connexion à la base de données
        String jdbcUrl = DataForConnection.ENDPOINT.getUrl();
        String username = DataForConnection.USER.getUrl();
        String password = DataForConnection.KEY.getUrl();

        // Le nom d'utilisateur à rechercher

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Charger le driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Établir la connexion à la base de données
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Requête SQL avec une clause WHERE pour rechercher par username
            String query = "SELECT UserID, password, username FROM `Loggin Credentials` WHERE username = ?";

            // Préparer la requête SQL avec le PreparedStatement
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, access);

            // Exécuter la requête et récupérer les résultats dans un ResultSet
            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);

            // Vérifier si un utilisateur est trouvé
            if (resultSet.next() && PasswordHash.checkPassword(hash, resultSet.getString("password"))) {
                //Recover User Data for the session
                String queryFromData = "SELECT * FROM `User Information` WHERE `Loggin Credentials_UserID` = ?";
                preparedStatement = connection.prepareStatement(queryFromData);
                System.out.println(resultSet.getString("UserID"));
                preparedStatement.setString(1, resultSet.getString("UserID"));

                ResultSet userData = preparedStatement.executeQuery();
                userData.next();
                if (userData.getInt("CityNumber") == -1) {
                    user = new Resident (userData.getString("FirstName"), userData.getString("LastName"),
                            userData.getString("ResidentialAddress"), userData.getString("ElectronicAddress"),
                            userData.getString("PhoneNumber"), userData.getInt("Loggin Credentials_UserID"),
                            userData.getDate("BirthDate"));
                }else {
                    user = new Manager(userData.getString("FirstName"), userData.getString("LastName"),
                            userData.getString("ResidentialAddress"), userData.getString("ElectronicAddress"),
                            userData.getString("PhoneNumber"), userData.getInt("Loggin Credentials_UserID"),
                            userData.getDate("BirthDate"), userData.getInt("CityNumber"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fermer les ressources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }
}
