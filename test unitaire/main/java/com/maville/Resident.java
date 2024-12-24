package com.maville;

public class Requete {
    private String titre;
    private String description;

    public Requete(String titre, String description) {
        this.titre = titre;
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }
}
