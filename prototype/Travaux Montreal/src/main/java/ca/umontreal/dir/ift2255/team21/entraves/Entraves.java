package ca.umontreal.dir.ift2255.team21.entraves;

import java.sql.Date;

public class Entraves {
    private String id;
    private String steetid;
    private double longitude;
    private double latitude;
    private String streetimpact;
    private Date dateDebut;
    private Date dateFin;

    public Entraves(String id, String steetid, double longitude, double latitude, String streetimpact, Date dateDebut, Date dateFin) {
        this.id = id;
        this.steetid = steetid;
        this.longitude = longitude;
        this.latitude = latitude;
        this.streetimpact = streetimpact;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public String getId() {
        return id;
    }
    public String getSteetid() {
        return steetid;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getStreetimpact() {
        return streetimpact;
    }

    public void setStreetimpact(String streetimpact) {
        this.streetimpact = streetimpact;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
}
