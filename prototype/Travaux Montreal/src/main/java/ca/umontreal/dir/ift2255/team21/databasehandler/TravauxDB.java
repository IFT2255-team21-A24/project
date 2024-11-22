package ca.umontreal.dir.ift2255.team21.databasehandler;

import ca.umontreal.dir.ift2255.team21.entraves.Travaux;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class TravauxDB {
    public static ArrayList<Travaux> retrieveTravaux() {
        ArrayList<Travaux> travauxArrayList = new ArrayList<>();
        // Informations de connexion à la base de données
        String jdbcUrl = DataForConnection.ENDPOINT.getUrl();
        String username = DataForConnection.USER.getUrl();
        String password = DataForConnection.KEY.getUrl();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(jdbcUrl,username,password);
            String sql = "SELECT * FROM Travaux";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while(rs.next()){
                String id = rs.getString("idTravaux");
                double latitude = rs.getDouble("latitude");
                double longitude = rs.getDouble("longitude");
                Date datedebut = rs.getDate("dateDebut");
                Date datefinal = rs.getDate("dateFin");
                String arrondissement = rs.getString("arrondissement");
                String currentStatus = rs.getString("currentStatus");
                String reasonCategory = rs.getString("reasonCategory");
                String submitCategory = rs.getString("submitCategory");
                String organisationName = rs.getString("organisationName");

                Travaux tmp = new Travaux(id, longitude, latitude,datedebut,datefinal,arrondissement,
                        currentStatus,reasonCategory,submitCategory,organisationName);
                travauxArrayList.add(tmp);

            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return travauxArrayList;
    }
}
