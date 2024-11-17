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
    public static ArrayList<Entraves> appelAPI() {
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
            JsonNode rootNode = mapper.readTree(response.body());  // Convertir les entraves en arbre JSON
            JsonNode rootNode2 = mapper.readTree(response2.body());  // Convertir les travaux en arbre JSON

            // Extraire les objets "records" de chaque réponse
            JsonNode recordsNode = rootNode.path("result").path("records"); //entraves
            JsonNode recordsNode2 = rootNode2.path("result").path("records"); // travaux
            System.out.println(recordsNode.toString());

            if (recordsNode.isArray()) {
                for (JsonNode record : recordsNode) {
                    // Ajouter les éléments de recordsNode à la map avec "id_request"

                }
            }

//            // Traiter recordsNode2 et remplir le tableau entraves
//            if (recordsNode2.isArray()) {
//                for (JsonNode record2 : recordsNode2) {
//                    String id = record2.path("id").asText();  // id dans recordsNode2
//                    JsonNode linkedRecord = recordsMap.get(id);  // Chercher l'objet correspondant dans recordsNode
//
//                    if (linkedRecord != null) {
//                        // Extraire les données des deux objets
//                        String streetimpact = linkedRecord.path("streetimpacttype").asText();
//                        String streetid = linkedRecord.path("streetid").asText();
//                        double longitude = record2.path("longitude").asDouble();
//                        double latitude = record2.path("latitude").asDouble();
//
//                        // Convertir les dates au format SQL
//                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                        Date dateDebut = Date.valueOf(sdf.format(sdf.parse(record2.path("duration_start_date").asText())));
//                        Date dateFin = Date.valueOf(sdf.format(sdf.parse(record2.path("duration_end_date").asText())));
//
//                        // Créer un nouvel objet Entraves et l'ajouter à la liste
//                        Entraves entrave = new Entraves(id, streetid, longitude, latitude, streetimpact, dateDebut, dateFin);
//                        entraves.add(entrave);
//                    }
//                }
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return entraves;
    }
}
