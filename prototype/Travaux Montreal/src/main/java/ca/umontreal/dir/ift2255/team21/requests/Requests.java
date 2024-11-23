package ca.umontreal.dir.ift2255.team21.requests;

import java.sql.Date;

public class Requests {
    private String name;
    private String type;
    private String address;
    private Double latitude;
    private Double longitude;
    private Date beginDate;

    public Requests(String name, String type, String address, Double latitude, Double longitude, Date beginDate) {
        this.name = name;
        this.type = type;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.beginDate = beginDate;
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
}
