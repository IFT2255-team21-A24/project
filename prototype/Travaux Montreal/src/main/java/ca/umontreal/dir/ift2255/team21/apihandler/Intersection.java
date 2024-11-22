package ca.umontreal.dir.ift2255.team21.apihandler;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;


import ca.umontreal.dir.ift2255.team21.databasehandler.DataForConnection;

public class Intersection {
    public static double[] calculerIntersection(String[] ruesDebut, String[] ruesFin ) {
        OkHttpClient client = new OkHttpClient();
        String adresse = "Intersection of " + String.join(" and ", ruesDebut) + ", Montreal, QC";
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" +
                adresse.replace(" ", "+") + "&language=en&key=" + DataForConnection.GOOGLEKEY.getUrl();
        Request request = new Request.Builder().url(url).get().build();
        double[] debut = coordinateReseaerch(request);

        adresse = "Intersection of " + String.join(" and ", ruesFin) + ", Montreal, QC";
        url = "https://maps.googleapis.com/maps/api/geocode/json?address=" +
                adresse.replace(" ", "+") + "&language=en&key=" + DataForConnection.GOOGLEKEY.getUrl();
        request = new Request.Builder().url(url).get().build();
        double[] fin = coordinateReseaerch(request);
        double lon_milieu = (debut[1]+fin[1])/2;
        double lat_milieu = (debut[0]+fin[0])/2;

        return new double[]{lat_milieu, lon_milieu};
    }
    private static double[] coordinateReseaerch(Request request){

        OkHttpClient client = new OkHttpClient();
        try (Response response = client.newCall(request).execute()){
            if(response.isSuccessful()){
                String responseString = response.body().string();

                JSONObject jsonObject = new JSONObject(responseString);
                JSONObject results = jsonObject.getJSONArray("results")
                        .getJSONObject(0).getJSONObject("geometry")
                        .getJSONObject("location");
                double lat = results.getDouble("lat");
                double lng = results.getDouble("lng");

                return new double[]{lat, lng};
            }
        }catch (IOException e){}
        return null;
    }
}
