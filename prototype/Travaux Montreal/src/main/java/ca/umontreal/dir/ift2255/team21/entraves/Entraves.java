package ca.umontreal.dir.ift2255.team21.entraves;

import java.sql.Date;

public class Entraves {
    private String steetid;
    private String streetimpact;
    private String streetname;
    private String id_request;
    private double latitude;
    private double longitude;

    public Entraves(String steetid, String streetimpact, String streetname, String id_request, double latitude, double longitude) {
        this.steetid = steetid;
        this.streetimpact = streetimpact;
        this.streetname = streetname;
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

    public String getStreetname() {
        return streetname;
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
}
