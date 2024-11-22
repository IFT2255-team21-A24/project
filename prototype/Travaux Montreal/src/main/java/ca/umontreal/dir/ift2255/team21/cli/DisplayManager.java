package ca.umontreal.dir.ift2255.team21.cli;

import ca.umontreal.dir.ift2255.team21.accounts.Manager;

import java.util.Scanner;

public class DisplayManager {
    public void homePageManager(Manager manager) {
        Scanner input = new Scanner(System.in);
        int choice;
        System.out.print("""
                //======================================================\\\\
                ||        Informations sur vos chantiers en cours       ||
                ||                 Région de Montréal :                 ||
                ||------------------------------------------------------||
                ||                                                      ||
                ||                    NOTIFICATIONS:                    ||
                ||                                                      ||
                ||    Le projet de construction que vous dirigez :      ||
                ||                   Accepté                            ||
                ||              Date de début : 19-10-2024              ||
                ||                                                      ||
                ||------------------------------------------------------||
                ||                                                      ||
                ||                Projets en cours :                    ||
                ||                                                      ||
                ||           1) Pont Jacques-Cartier :                  ||
                ||  Objectif : Réparation du tablier principal.         ||
                ||            Date de fin prévue : 15-12-2024           ||
                ||                                                      ||
                ||           2) Boulevard René-Lévesque :               ||
                ||  Objectif : Installation de nouveaux lampadaires     ||
                ||            Date de fin prévue : 08-11-2024           ||
                ||                                                      ||
                ||           3) Rue Sherbrooke Ouest :                  ||
                ||  Objectif : Réfection de la chaussée                 ||
                ||            Date de fin prévue : 25-10-2024           ||
                ||                                                      ||
                ||------------------------------------------------------||
                ||  1) Consultation des travaux                         ||
                ||  2) Soumettre une nouvelle requête                   ||
                ||  3) Consulter la liste des requêtes                  ||
                ||  4) Se déconnecter                                   ||
                ||                                                      ||
                \\\\======================================================//
                        Votre choix :   """);

        choice = input.nextInt();
        clearScreen();

        switch (choice){
            case 1:
                travauxManager(manager);
                break;
            case 2:
                soumissionRequete(manager);
                break;
            case 3:
                consulterRequete(manager);
                break;
            case 4:
                manager = null;
                break;
            default:
                System.err.println("Choix indisponible.\n");
                homePageManager(manager);
                break;
        }
    }
    public void travauxManager(Manager manager) {
        Scanner input = new Scanner(System.in);
        int choice;
        System.out.print("""
                //================================================================\\\\
                ||           Travaux en cours/à venir dans votre région           ||
                ||                      Ville de Montréal                         ||
                ||----------------------------------------------------------------||
                ||                                                                ||
                ||                        TRAVAUX EN COURS:                       ||
                ||                                                                ||
                ||  1 - Rue Sainte-Catherine Est :                                ||
                ||     Objectif : Réparer l'aqueduc au croisement                 ||
                ||                Sainte-Catherine Est / Berri                    ||
                ||     Date de début : 10-10-2024                                 ||
                ||     Date de fin prévue : 23-01-2025                            ||
                ||                                                                ||
                ||  2 - Boulevard Saint-Laurent :                                 ||
                ||     Objectif : Réfection de la chaussée.                       ||
                ||     Date de début : 01-09-2024                                 ||
                ||     Date de fin prévue : 20-11-2024                            ||
                ||                                                                ||
                ||----------------------------------------------------------------||
                ||                                                                ||
                ||                    TRAVAUX À VENIR:                            ||
                ||                                                                ||
                ||  1 - Avenue du Parc :                                          ||
                ||     Objectif : Remplacement des conduites d'eau.               ||
                ||     Date de début : 05-11-2024                                 ||
                ||     Date de fin prévue : 15-12-2024                            ||
                ||                                                                ||
                ||  2 - Rue Notre-Dame Ouest :                                    ||
                ||     Objectif : Installation de nouvelles pistes cyclables.     ||
                ||     Date de début : 20-11-2024                                 ||
                ||     Date de fin prévue : 15-03-2025                            ||
                ||                                                                ||
                ||----------------------------------------------------------------||
                ||                                                                ||
                ||   1) Plus de détails sur un projet                             ||
                ||   2) Revenir à l'accueil                                       ||
                ||                                                                ||
                \\\\================================================================//
                        Votre choix :   """);

        choice = input.nextInt();

        switch (choice) {
            case 1:
                exempleTravail(manager);
                break;
            case 2:
                homePageManager(manager);
                break;
            default:
                System.err.println("Votre choix est introuvable");
                travauxManager(manager);
                break;
        }

        clearScreen();
    }

    public void soumissionRequete(Manager manager) {
        Scanner input = new Scanner(System.in);
        int choice;
        System.out.print("""
                //=============================================================\\\\
                ||           Soumission de requête pour un projet              ||
                ||                 Ville de Montréal                           ||
                ||-------------------------------------------------------------||
                ||                                                             ||
                ||   1) Nom du projet : _________________________________      ||
                ||                                                             ||
                ||   3) Type de projet :                                       ||
                ||      [ ] Réparation de routes                               ||
                ||      [ ] Réseau d'aqueduc                                   ||
                ||      [ ] Conduites de gaz                                   ||
                ||      [ ] Installation de lampadaires                        ||
                ||      [ ] Autre : ___________________________________        ||
                ||                                                             ||
                ||   5) Rues affectées : _________________________________     ||
                ||                       ___________________________________   ||
                ||                                                             ||
                ||   6) Date de début : ____/____/______                       ||
                ||                                                             ||
                ||   7) Date de fin : ____/____/______                         ||
                ||                                                             ||
                ||   8) Horaire des travaux : _____________________________    ||
                ||                         _________________________________   ||
                ||                                                             ||
                ||-------------------------------------------------------------||
                ||   # Ici, choisissez le numéro du champ de text à modifier   ||
                ||   9) Soumettre la requête                                   ||
                ||   0) Annuler                                                ||
                ||                                                             ||
                \\\\===========================================================//
                        Votre choix :   """);

        choice = input.nextInt();

        clearScreen();
        switch (choice) {
            case 0,1,2,3,4,5,6,7,8,9:
                homePageManager(manager);
                break;
            default:
                System.err.println("Votre choix est introuvable");
                soumissionRequete(manager);
                break;
        }
    }

    public void consulterRequete(Manager manager) {
        Scanner input = new Scanner(System.in);
        int choice;
        System.out.print("""
                //=================================================================\\\\
                ||             Liste des requêtes des travaux soumises             ||
                ||                        Ville de Montréal                        ||
                ||-----------------------------------------------------------------||
                ||                                                                 ||
                ||   1- Rue Sherbrooke Ouest - Réparation de l'éclairage public    ||
                ||      Soumis par : Marc Dupuis                                   ||
                ||      Date de soumission : 12/09/2024                            ||
                ||      Statut : En attente                                        ||
                ||                                                                 ||
                ||   2- Boulevard René-Lévesque - Réfection de la chaussée         ||
                ||      Soumis par : Julie Martin                                  ||
                ||      Date de soumission : 23/08/2024                            ||
                ||      Statut : En cours                                          ||
                ||                                                                 ||
                ||   3- Rue Sainte-Catherine - Reconfiguration des pistes cyclables||
                ||      Soumis par : Luc Tremblay                                  ||
                ||      Date de soumission : 30/09/2024                            ||
                ||      Statut : Approuvé                                          ||
                ||                                                                 ||
                ||   4- Avenue du Parc - Remplacement des conduites d'eau          ||
                ||      Soumis par : Sophie Roy                                    ||
                ||      Date de soumission : 18/09/2024                            ||
                ||      Statut : Refusé                                            ||
                ||                                                                 ||
                ||-----------------------------------------------------------------||
                ||                                                                 ||
                ||   1) Voir plus de détails sur une requête                       ||
                ||   2) Soumettre la candidature pour une requête                  ||
                ||   3) Retourner au menu principal                                ||
                ||                                                                 ||
                \\\\=================================================================//
                        Votre choix :   """);

        choice = input.nextInt();

        clearScreen();
        switch (choice) {
            case 1,2,3:
                homePageManager(manager);
                break;
            default:
                System.err.println("Votre choix est introuvable");
                consulterRequete(manager);
                break;
        }
    }

    public void exempleTravail(Manager manager) {
        Scanner input = new Scanner(System.in);
        int choice;
        System.out.print("""
                //=================================================================\\\\
                ||                   Détails du travail en cours                   ||
                ||                        Ville de Montréal                        ||
                ||-----------------------------------------------------------------||
                ||                                                                 ||
                ||   1- Titre du projet : Réparation de l'éclairage public         ||
                ||                                                                 ||
                ||   2- Description : Remplacer les lampadaires défectueux sur     ||
                ||      Rue Sherbrooke Ouest, entre Guy et Atwater.                ||
                ||                                                                 ||
                ||   3- Type de travaux : Maintenance de l'infrastructure          ||
                ||                                                                 ||
                ||   4- Quartiers affectés : Ville-Marie                           ||
                ||                                                                 ||
                ||   5- Rue(s) affectée(s) : Rue Sherbrooke Ouest                  ||
                ||                                                                 ||
                ||   6- Date de début : 15/10/2024                                 ||
                ||                                                                 ||
                ||   7- Date de fin prévue : 30/10/2024                            ||
                ||                                                                 ||
                ||   8- Horaire des travaux : Lundi - Vendredi, 8h à 17h           ||
                ||                                                                 ||
                ||   9- Intervenant responsable : Paul Leblanc                    ||
                ||                                                                 ||
                ||-----------------------------------------------------------------||
                ||                                                                 ||
                ||   1) Mettre à jour le titre du projet                           ||
                ||   2) Mettre à jour la description                               ||
                ||   3) Mettre à jour le type de travaux                           ||
                ||   4) Mettre à jour les quartiers affectés                       ||
                ||   5) Mettre à jour les rues affectées                           ||
                ||   6) Mettre à jour la date de début                             ||
                ||   7) Mettre à jour la date de fin prévue                        ||
                ||   8) Mettre à jour l'horaire des travaux                        ||
                ||   9) Mettre à jour l'intervenant responsable                    ||
                ||-----------------------------------------------------------------||
                ||                                                                 ||
                ||   0) Retourner à la liste des travaux                           ||
                ||                                                                 ||
                \\\\=================================================================//
                         Votre choix :    """);
        choice = input.nextInt();
        clearScreen();
        switch (choice) {
            case 0,1,2,3,4,5,6,7,8,9:
                homePageManager(manager);
                break;
            default:
                System.err.println("Votre choix est introuvable");
                exempleTravail(manager);
                break;
        }
    }

    public static void clearScreen() {
        try {
            final String system = System.getProperty("os.name").toLowerCase();
            if (system.contains("windows")) {
                Runtime.getRuntime().exec("cls");
            }
        }catch (Exception e) {}
    }

}
