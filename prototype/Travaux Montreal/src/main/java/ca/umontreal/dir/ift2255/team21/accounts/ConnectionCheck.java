package ca.umontreal.dir.ift2255.team21.accounts;

public class ConnectionCheck {
    private String username;
    private String password;

    public ConnectionCheck(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
