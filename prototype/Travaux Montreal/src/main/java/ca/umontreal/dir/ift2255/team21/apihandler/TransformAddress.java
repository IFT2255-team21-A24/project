package ca.umontreal.dir.ift2255.team21.apihandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

public class TransformAddress {
    public String transform(String address) {
        String coordinates = "";
        try {
            JSONObject jsonObject = getJsonObject(address);
            JSONObject location = jsonObject.getJSONArray("results").getJSONObject(0).
                    getJSONObject("geometry").getJSONObject("location");
            
            double lat = location.getDouble("lat");
            double lng = location.getDouble("lng");
            
            coordinates = lat + "," + lng;
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return coordinates;
    }

    @NotNull
    private static JSONObject getJsonObject(String address) throws IOException {
        String key = "AIzaSyAgCK4KJXdSOjAUL5jKwyRxY3cIaBSsWlE";
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address="
                + address.replace(" ", "+") + "&key=" + key;
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        JSONObject jsonObject = new JSONObject(content.toString());
        return jsonObject;
    }
}
