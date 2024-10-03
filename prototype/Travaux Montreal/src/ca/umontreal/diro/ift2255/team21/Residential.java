package ca.umontreal.diro.ift2255.team21;

import java.util.Date;

public class Residential extends Account{
    public Residential(String first_name, String last_name, String residential_adress, String electronic_adress, int[] phone_number, int id, Date date_naissance) {
        super(first_name, last_name, residential_adress, electronic_adress, phone_number, id, date_naissance);
    }
}
