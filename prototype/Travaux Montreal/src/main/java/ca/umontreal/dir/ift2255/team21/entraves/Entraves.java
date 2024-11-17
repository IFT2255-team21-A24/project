package ca.umontreal.dir.ift2255.team21.entraves;

import java.sql.Date;

public class Entraves extends Travaux {
    private String steetid;
    private String streetimpact;

    public Entraves(String id, String steetid, double longitude, double latitude,
                    String streetimpact, Date dateDebut, Date dateFin) {
        super(id, longitude, latitude, dateDebut, dateFin);
        this.steetid = steetid;
        this.streetimpact = streetimpact;

    }


    public String getSteetid() {
        return steetid;
    }

    public String getStreetimpact() {
        return streetimpact;
    }

    public void setStreetimpact(String streetimpact) {
        this.streetimpact = streetimpact;
    }
}
