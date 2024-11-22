package ca.umontreal.dir.ift2255.team21.databasehandler;

import ca.umontreal.dir.ift2255.team21.entraves.Entraves;
import ca.umontreal.dir.ift2255.team21.entraves.Travaux;

import java.sql.*;
import java.util.ArrayList;

public class EntravesDB {
    public static ArrayList<Entraves> retrieveAllEntraves() {
        ArrayList<Entraves> entravesArrayList = new ArrayList<>();
        // Informations de connexion à la base de données
        String jdbcUrl = DataForConnection.ENDPOINT.getUrl();
        String username = DataForConnection.USER.getUrl();
        String password = DataForConnection.KEY.getUrl();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(jdbcUrl, username, password);
            String sql = "SELECT * FROM Entraves";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String id = rs.getString("idEntraves");
                String streetname = rs.getString("streetname");
                String neighberhood = rs.getString("neighberhood");
                String streetImpactType = rs.getString("streetImpactType");
                double latitude = rs.getDouble("latitude");
                double longitude = rs.getDouble("longitude");
                Entraves tmp = new Entraves(streetname, streetImpactType, neighberhood, id, latitude, longitude);
                entravesArrayList.add(tmp);

            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entravesArrayList;
    }
}
