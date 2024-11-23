package ca.umontreal.dir.ift2255.team21.apihandler;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import ca.umontreal.dir.ift2255.team21.databasehandler.DataForConnection;
import org.json.JSONArray;
import org.json.JSONObject;
public class AddressVerificator {
    public static boolean verifyAddress(String address) {

        try {
        // URL de requête
        String urlString = "https://maps.googleapis.com/maps/api/geocode/json?address="
                + address.replace(" ", "+") + "&key=" + DataForConnection.GOOGLEKEY;

        // Créer une connexion HTTP
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // Lire la réponse
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Parse la réponse JSON
        JSONObject json = new JSONObject(response.toString());
        String status = json.getString("status");

        if ("OK".equals(status)) {
            JSONArray results = json.getJSONArray("results");
            if (results.length() > 0) {
                return true;
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
        return false;
    }
}
