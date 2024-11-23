package ca.umontreal.dir.ift2255.team21.entraves;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Entraves {
    private String steetid;
    private String streetimpact;
    private String neighborhood;
    private String id_request;
    private double latitude;
    private double longitude;

    public Entraves(String steetid, String streetimpact, String neighborhood, String id_request, double latitude, double longitude) {
        this.steetid = steetid;
        this.streetimpact = streetimpact;
        this.neighborhood = neighborhood;
        this.id_request = id_request;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getSteetid() {
        return steetid;
    }

    public String getStreetimpact() {
        return streetimpact;
    }

    public String getneighborhood() {
        return neighborhood;
    }

    public String getId_request() {
        return id_request;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int maxWidth = 100; // Largeur maximale pour les colonnes

        sb.append(formatLine(String.format("Entrave en cours sur %s:", steetid), maxWidth, true));
        sb.append(formatLine("Impact : " + streetimpact, maxWidth, false));
        sb.append(formatLine("Quartier : " + neighborhood, maxWidth, false));
        sb.append(formatLine("ID : " + id_request, maxWidth, false));
        sb.append(formatLine(neighborhood, maxWidth, true));

        return sb.toString();
    }

    private String formatLine(String content, int maxWidth, boolean indent) {
        List<String> lines = splitIntoLines(content, maxWidth);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < lines.size(); i++) {
            if (i == 0) {
                result.append(String.format("||  %-100s  ||\n", indent ? "    " + lines.get(i) : lines.get(i)));
            } else {
                result.append(String.format("||     %-96s  ||\n", lines.get(i)));
            }
        }
        return result.toString();
    }

    private List<String> splitIntoLines(String text, int maxWidth) {
        List<String> lines = new ArrayList<>();
        int start = 0;
        while (start < text.length()) {
            int end = Math.min(start + maxWidth, text.length());
            String line = text.substring(start, end);
            lines.add(line);
            start = end;
        }
        return lines;
    }
}
