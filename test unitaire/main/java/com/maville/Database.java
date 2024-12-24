package com.maville;

import java.util.List;

public interface Database {
    boolean ajouterResident(Resident resident);
    boolean enregistrerRequete(Resident resident, Requete requete);
    List<Requete> recupererRequetes();
}
