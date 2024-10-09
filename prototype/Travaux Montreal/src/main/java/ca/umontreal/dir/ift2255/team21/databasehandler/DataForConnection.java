package ca.umontreal.dir.ift2255.team21.databasehandler;

public enum DataForConnection {
    ENDPOINT("jdbc:mysql://ift2255.cfm0oqm68j83.us-east-2.rds.amazonaws.com:3306/DataUser?useSSL=false&serverTimezone=UTC"),
    USER("admin"),
    KEY("Team21#UdeM2255");
    private String url;

    DataForConnection(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }

}
