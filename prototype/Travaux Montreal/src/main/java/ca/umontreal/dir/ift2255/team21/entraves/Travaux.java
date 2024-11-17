package ca.umontreal.dir.ift2255.team21.entraves;

import java.sql.Date;

public class Travaux {
    private String id;
    private double longitude;
    private double latitude;
    private Date dateDebut;
    private Date dateFin;

    public Travaux(String id, double longitude, double latitude, Date dateDebut, Date dateFin) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
}
