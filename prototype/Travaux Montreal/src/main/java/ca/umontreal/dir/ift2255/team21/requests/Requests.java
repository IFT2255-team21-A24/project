package ca.umontreal.dir.ift2255.team21.requests;

import java.sql.Date;

public class Requests {
    private String name;
    private String type;
    private String address;
    private Double latitude;
    private Double longitude;
    private Date beginDate;
    private String status;

    public Requests(String name, String type, String address, Double latitude, Double longitude, Date beginDate, String status) {
        this.name = name;
        this.type = type;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.beginDate = beginDate;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getAddress() {
        return address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(formatLine("Nom : " + name));
        sb.append(formatLine("Type : " + type));
        sb.append(formatLine("Adresse : " + address));
        sb.append(formatLine("Date de début : " + beginDate));
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
