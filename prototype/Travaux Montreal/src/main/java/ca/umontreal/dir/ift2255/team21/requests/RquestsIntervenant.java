package ca.umontreal.dir.ift2255.team21.requests;

import java.sql.Date;

public class RquestsIntervenant {
    private String requestName;
    private String requestDescription;
    private String[] rues;
    private String status;
    private Date requestDate;

    public RquestsIntervenant(String requestName, String requestDescription, String[] rues, String status, Date requestDate) {
        this.requestName = requestName;
        this.requestDescription = requestDescription;
        this.rues = rues;
        this.status = status;
        this.requestDate = requestDate;
    }

    public String getRequestName() {
        return requestName;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public String[] getRues() {
        return rues;
    }

    public String getStatus() {
        return status;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(formatLine("Nom : " + requestName));
        sb.append(formatLine("Type : " + requestDescription));
        for (String rue : rues) {
            sb.append(formatLine("Rue : " + rue));
        }
        sb.append(formatLine("Date de début : " + requestDate));
        sb.append(formatLine("Statut : " + status));

        return sb.toString();
    }
    private String formatLine(String content) {
        int maxWidth = 100; // Largeur maximale pour chaque ligne
        StringBuilder sb = new StringBuilder();

        if (content.length() > maxWidth) {
            // Découpe le texte en plusieurs lignes si nécessaire
            int start = 0;
            while (start < content.length()) {
                int end = Math.min(start + maxWidth, content.length());
                String line = content.substring(start, end);
                sb.append(String.format("||     %-96s  ||\n", line));
                start = end;
            }
        } else {
            sb.append(String.format("||     %-96s  ||\n", content));
        }

        return sb.toString();
    }
}
