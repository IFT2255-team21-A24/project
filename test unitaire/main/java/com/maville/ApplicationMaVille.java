package com.maville;

import java.util.List;

public class ApplicationMaVille {
    private Database database;
    private NotificationService notificationService;

    public ApplicationMaVille(Database database, NotificationService notificationService) {
        this.database = database;
        this.notificationService = notificationService;
    }

    public boolean inscrireResident(Resident resident) {
        return database.ajouterResident(resident);
    }

    public boolean soumettreRequete(Resident resident, Requete requete) {
        return database.enregistrerRequete(resident, requete);
    }

    public List<Requete> recupererRequetes() {
        return database.recupererRequetes();
    }

    public boolean notifierUtilisateur(String email, String message) {
        return notificationService.envoyerNotification(email, message);
    }

    public static void main(String[] args) {
        System.out.println("Bienvenue dans l'application MaVille !");
    }
}
