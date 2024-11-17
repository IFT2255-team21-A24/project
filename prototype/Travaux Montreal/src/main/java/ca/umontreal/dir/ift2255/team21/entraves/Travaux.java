package ca.umontreal.dir.ift2255.team21.entraves;

import java.sql.Date;

public class Travaux {
    private String id;
    private double longitude;
    private double latitude;
    private Date dateDebut;
    private Date dateFin;
    private String boroughid;
    private String currentstatus;
    private String reason_category;
    private String submitcategory;
    private String organisationname;

    public Travaux(String id, double longitude, double latitude, Date dateDebut, Date dateFin,
                   String boroughid, String currentstatus, String reason_category,
                   String submitcategory, String organisationname) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.boroughid = boroughid;
        this.currentstatus = currentstatus;
        this.reason_category = reason_category;
        this.submitcategory = submitcategory;
        this.organisationname = organisationname;
    }

    public String getId() {
        return id;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public String getBoroughid() {
        return boroughid;
    }

    public String getCurrentstatus() {
        return currentstatus;
    }

    public String getReason_category() {
        return reason_category;
    }

    public String getSubmitcategory() {
        return submitcategory;
    }

    public String getOrganisationname() {
        return organisationname;
    }
}
