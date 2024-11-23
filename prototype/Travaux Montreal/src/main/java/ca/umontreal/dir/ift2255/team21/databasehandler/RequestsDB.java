package ca.umontreal.dir.ift2255.team21.databasehandler;

import ca.umontreal.dir.ift2255.team21.entraves.Travaux;
import ca.umontreal.dir.ift2255.team21.requests.Requests;

import java.sql.*;
import java.util.ArrayList;

public class RequestsDB {
    private static ArrayList<Requests> retrieveRequests() {
        ArrayList<Requests> requests = new ArrayList<>();

        String jdbcUrl = DataForConnection.ENDPOINT.getUrl();
        String username = DataForConnection.USER.getUrl();
        String password = DataForConnection.KEY.getUrl();

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(jdbcUrl,username,password);
            String sql = "SELECT * FROM WorkRequests";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while(rs.next()){
                String name = rs.getString("WorkTitle");
                double latitude = rs.getDouble("latitude");
                double longitude = rs.getDouble("longitude");
                Date datedebut = rs.getDate("StartDate");
                String type = rs.getString("WorkType");
                String address = rs.getString("Address");
                String status = rs.getString("Status");

                Requests tmp = new Requests(name, type, address, latitude, longitude, datedebut, status);
                requests.add(tmp);

            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return requests;
    }
}
