package ca.umontreal.diro.ift2255.team21;

import java.util.Date;

public class Representent extends Account{
    private int city_indentification;
    public Representent(String first_name, String last_name, String residential_adress, String electronic_adress,
                        int[] phone_number, int id, Date date_naissance, int city_indentification) {
        super(first_name, last_name, residential_adress, electronic_adress, phone_number, id, date_naissance);
        this.city_indentification = city_indentification;
    }
}
