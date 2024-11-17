package ca.umontreal.dir.ift2255.team21.distancecalculator;
import ca.umontreal.dir.ift2255.team21.apihandler.ListerEntraves;
import ca.umontreal.dir.ift2255.team21.databasehandler.DataForConnection;
import ca.umontreal.dir.ift2255.team21.entraves.Entraves;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

public class CalculateDistance {


    public static ArrayList<Entraves> listerEntravesProches(ArrayList<Entraves> entraves, String coordinates) {
        ArrayList<Entraves> entravesProches = new ArrayList<>();
        String quartierClient = trouverQuartier(coordinates);
        for (Entraves entrave : entraves) {
            String coordinatesEntrave = entrave.getLatitude() + "," + entrave.getLongitude();
            String quartierTravaux = trouverQuartier(coordinatesEntrave);
            if (quartierTravaux.equals(quartierClient)) {
                entravesProches.add(entrave);
            }
        }

        return entravesProches;
    }

    private static String trouverQuartier(String coord) {
        OkHttpClient client = new OkHttpClient();
        String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + coord + "&key=" +
                DataForConnection.GOOGLEKEY.getUrl();

        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null) {
                String json = response.body().string();
                JSONObject jsonObject = new JSONObject(json);
                JSONArray results = jsonObject.getJSONArray("results");
                if (results.length() > 0) {
                    JSONObject firstResult = results.getJSONObject(0);
                    JSONArray addresses = firstResult.getJSONArray("address_components");
                    if (addresses.length() > 2) {
                        JSONObject quartier = addresses.getJSONObject(2);
                        String quartierTravaux = quartier.getString("long_name");
                        System.out.println(quartierTravaux);
                        return quartierTravaux;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        ArrayList<Entraves> listerEntraves = new ArrayList<>();
        listerEntraves = ListerEntraves.appelAPI();
        System.out.println(listerEntraves);
        String test = "45.52049513444428,-73.74041890999476";
        listerEntraves = listerEntravesProches(listerEntraves, test);
        System.out.println(listerEntraves);
    }


}
