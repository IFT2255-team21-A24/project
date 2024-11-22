package ca.umontreal.dir.ift2255.team21.entraves;

import java.sql.Date;

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
        return steetid +
                "steetid='" + steetid + '\'' +
                ", streetimpact='" + streetimpact + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", id_request='" + id_request + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
