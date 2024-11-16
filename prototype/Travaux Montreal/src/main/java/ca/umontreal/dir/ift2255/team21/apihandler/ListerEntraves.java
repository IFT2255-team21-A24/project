package ca.umontreal.dir.ift2255.team21.apihandler;

import ca.umontreal.dir.ift2255.team21.entraves.Entraves;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListerEntraves {
    private ArrayList<Entraves> appelAPI() {
        ArrayList<Entraves> entraves = new ArrayList<>();
        try {
            // Créer un client HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Créer une requête GET pour les entraves
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://donnees.montreal.ca/api/3/action/datastore_search?resource_id=a2bc8014-488c-495d-941b-e7ae1999d1bd"))
                    .build();
            // Créer une requête GET pour les travaux en général
            HttpRequest request2 = HttpRequest.newBuilder()
                    .uri(new URI("https://donnees.montreal.ca/api/3/action/datastore_search?resource_id=cc41b532-f12d-40fb-9f55-eb58c9a2b12b"))
                    .build();

            // Envoyer la requête et obtenir la réponse
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            HttpResponse<String> response2 = client.send(request2, HttpResponse.BodyHandlers.ofString());

            // Utiliser Jackson pour analyser la réponse JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response.body());  // Convertir la réponse en arbre JSON
            JsonNode rootNode2 = mapper.readTree(response2.body());  // Convertir la réponse 2 en arbre JSON

            // Extraire les objets "records" de chaque réponse
            JsonNode recordsNode = rootNode.path("result").path("records");
            JsonNode recordsNode2 = rootNode2.path("result").path("records");

            // Créer une map pour lier les entrées entre recordsNode et recordsNode2 avec l'id
            Map<String, JsonNode> recordsMap = new HashMap<>();
            if (recordsNode2.isArray()) {
                for (JsonNode record : recordsNode2) {
                    // Ajouter les éléments de recordsNode2 à la map avec l'id
                    String id = record.path("id").asText();
                    recordsMap.put(id, record);
                }
            }

            // Traiter recordsNode et remplir le tableau entraves
            if (recordsNode.isArray()) {
                for (JsonNode record : recordsNode) {
                    String idRequest = record.path("id_request").asText();  // id_request dans recordsNode
                    JsonNode linkedRecord = recordsMap.get(idRequest);  // Chercher l'objet correspondant dans recordsNode2

                    if (linkedRecord != null) {
                        // Extraire les données des deux objets
                        String streetimpact = record.path("streetimpacttype").asText();
                        String streetid = record.path("streetid").asText();
                        double longitude = linkedRecord.path("longitude").asDouble();
                        double latitude = linkedRecord.path("latitude").asDouble();
                        String id = linkedRecord.path("id").asText();

                        // Convertir les dates au format SQL
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date dateDebut = Date.valueOf(sdf.format(sdf.parse(linkedRecord.path("duration_start_date").asText())));
                        Date dateFin = Date.valueOf(sdf.format(sdf.parse(linkedRecord.path("duration_end_date").asText())));

                        // Créer un nouvel objet Entraves et l'ajouter à la liste
                        Entraves entrave = new Entraves(id, streetid, longitude, latitude, streetimpact, dateDebut, dateFin);
                        entraves.add(entrave);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return entraves;
    }
}
