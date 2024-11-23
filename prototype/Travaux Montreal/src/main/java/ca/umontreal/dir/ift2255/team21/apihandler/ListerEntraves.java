package ca.umontreal.dir.ift2255.team21.apihandler;

import ca.umontreal.dir.ift2255.team21.databasehandler.InsertData;
import ca.umontreal.dir.ift2255.team21.distancecalculator.CalculateDistance;
import ca.umontreal.dir.ift2255.team21.entraves.Entraves;
import ca.umontreal.dir.ift2255.team21.entraves.Travaux;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListerEntraves {

    //permet d'accéder à la mise à jour de la base de donnée
    public static ArrayList<Entraves> appelAPIEntraves() {
        ArrayList<Entraves> entraves = new ArrayList<>();
        try {
            // Créer un client HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Créer une requête GET pour les entraves
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://donnees.montreal.ca/api/3/action/datastore_search?resource_id=a2bc8014-488c-495d-941b-e7ae1999d1bd"))
                    .build();

            // Envoyer la requête et obtenir la réponse
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Utiliser Jackson pour analyser la réponse JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response.body());  // Convertir les entraves en arbre JSON

            // Extraire les objets "records" de chaque réponse
            JsonNode recordsNode = rootNode.path("result").path("records"); //entraves

            if (recordsNode.isArray()) {
                for (JsonNode record : recordsNode) {
                    // Ajouter les éléments de recordsNode à la map avec "id_request"
                    String id = record.path("id_request").asText();
                    String streetimpact = record.path("streetimpacttype").asText();
                    String streetid = record.path("streetid").asText();
                    String fromname = record.path("fromname").asText();
                    String toname = record.path("toname").asText();
                    double[] coordinates = Intersection.calculerIntersection(new String[]{fromname, streetid}, new String[]{toname, streetid});
                    double latitude = coordinates[0];
                    double longitude = coordinates[1];
                    String neighborhood = CalculateDistance.trouverQuartier(latitude + "," + longitude);
                    Entraves entraves1 = new Entraves(streetid, streetimpact, neighborhood, id, latitude, longitude);
                    entraves.add(entraves1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return entraves;
    }

    public static ArrayList<Travaux> appelApiTravaux() {
        ArrayList<Travaux> travaux = new ArrayList<>();
        try {
            // Créer un client HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Créer une requête GET pour les entraves
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://donnees.montreal.ca/api/3/action/datastore_search?resource_id=cc41b532-f12d-40fb-9f55-eb58c9a2b12b"))
                    .build();

            // Envoyer la requête et obtenir la réponse
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Utiliser Jackson pour analyser la réponse JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response.body());  // Convertir les entraves en arbre JSON

            // Extraire les objets "records" de chaque réponse
            JsonNode recordsNode = rootNode.path("result").path("records"); //entraves

            if (recordsNode.isArray()) {
                for (JsonNode record : recordsNode) {
                    // Ajouter les éléments de recordsNode à la map avec "id_request"
                    String id = record.path("id").asText();
                    double latitude = record.path("latitude").asDouble();
                    double longitude = record.path("longitude").asDouble();
                    String jsonTimeBeggining = record.path("duration_start_date").asText();
                    String jsonTimeEnd = record.path("duration_end_date").asText();
                    OffsetDateTime offsetDateTime = OffsetDateTime.parse(jsonTimeBeggining);
                    Date beggining = Date.valueOf(offsetDateTime.toLocalDate());
                    offsetDateTime = offsetDateTime.parse(jsonTimeEnd);
                    Date end = Date.valueOf(offsetDateTime.toLocalDate());
                    String boroughid = record.path("boroughid").asText();
                    String currentStatus = record.path("currentstatus").asText();
                    String reason = record.path("reason_category").asText();
                    String submittercategory = record.path("submittercategory").asText();
                    String organizationname = record.path("organizationname").asText();
                    Travaux travaux1 = new Travaux(id,longitude,latitude,beggining,end,boroughid,currentStatus,
                            reason,submittercategory,organizationname);
                    travaux.add(travaux1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return travaux;
    }

}
