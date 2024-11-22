package ca.umontreal.dir.ift2255.team21.accounts;

import java.sql.Date;

public class Account {
    private String first_name, last_name, residential_adress, electronic_adress;
    private String phone_number;
    private int id;
    private Date date_naissance;

    public Account(String first_name, String last_name, String residential_adress, String electronic_adress, String phone_number, int id, Date date_naissance) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.residential_adress = residential_adress;
        this.electronic_adress = electronic_adress;
        this.phone_number = phone_number;
        this.id = id;
        this.date_naissance = date_naissance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", residential_adress='" + residential_adress + '\'' +
                ", electronic_adress='" + electronic_adress + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", id=" + id +
                ", date_naissance=" + date_naissance +
                '}';
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getResidential_adress() {
        return residential_adress;
    }

    public String getElectronic_adress() {
        return electronic_adress;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public int getId() {
        return id;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }
}
