package ca.umontreal.dir.ift2255.team21.cli;

import ca.umontreal.dir.ift2255.team21.accounts.Resident;
import ca.umontreal.dir.ift2255.team21.apihandler.TransformAddress;
import ca.umontreal.dir.ift2255.team21.distancecalculator.CalculateDistance;
import ca.umontreal.dir.ift2255.team21.entraves.Entraves;
import ca.umontreal.dir.ift2255.team21.entraves.Travaux;

import java.util.ArrayList;
import java.util.Scanner;

public class DisplayResident {
    public void homePageResident(Resident resident, ArrayList<Travaux> travauxArrayList,
                                 ArrayList<Entraves> entravesArrayList) {
        Scanner input = new Scanner(System.in);
        String entete = """
        //========================================================================================================\\\\
        ||                                     Nouveautés sur les constructions                                   ||
        ||                                          Dans votre région :                                           ||
        ||--------------------------------------------------------------------------------------------------------||
        ||--------------------------------------------------------------------------------------------------------||
        ||                                      Entraves dans votre quartier :                                    ||
        ||--------------------------------------------------------------------------------------------------------||
        """;
        TransformAddress transformAddress = new TransformAddress();
        String addrCoordinate = transformAddress.transform(resident.getResidential_adress());
        ArrayList<Entraves> entravesProches = CalculateDistance.listerEntravesProches(entravesArrayList, addrCoordinate);

        String footer = """
        ||--------------------------------------------------------------------------------------------------------||
        ||  1) Consultation des travaux en cours/à venir.                                                         ||
        ||  2) Consultation des entraves.                                                                         ||
        ||  3) Soumission de requête                                                                              ||
        ||  4) Planification participative                                                                        ||
        ||  5) Se déconnecter                                                                                     ||
        ||                                                                                                        ||
        \\\\========================================================================================================//
                                    Votre choix :   """;

        int choice;
        String affichage = entete;
        for (Entraves travaux : entravesProches) {
            affichage += travaux.toString();
            affichage += "||--------------------------------------------------------------------------------------------------------||\n";
        }
        affichage += footer;
        System.out.print(affichage);
        choice = input.nextInt();
        clearScreen();
        switch (choice){
            case 1:
                travauxResident(resident,travauxArrayList,entravesArrayList);
                break;
            case 3:
                soumissionRequete(resident, travauxArrayList, entravesArrayList);
                break;
            case 4:
                participation(resident, travauxArrayList,entravesArrayList);
                break;
            case 5:
                resident = null;
                break;
            default:
                System.out.println("Votre choix est introuvable");
                homePageResident(resident, travauxArrayList, entravesArrayList);
                break;
        }


    }
    public void travauxResident(Resident resident, ArrayList<Travaux> travauxArrayList,
                                ArrayList<Entraves> entravesArrayList) {
        Scanner scanner = new Scanner(System.in);
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
                ||   2) Rechercher un/des projet(s)                               ||
                ||   3) Revenir à l'accueil                                       ||
                ||                                                                ||
                \\\\================================================================//
                        Votre choix :   """);
        choice = scanner.nextInt();
        clearScreen();
        switch (choice){
            case 1:
                exempleTravaux(resident, travauxArrayList, entravesArrayList);
                break;
            case 2:
                rechercheTravaux(resident, travauxArrayList, entravesArrayList);
                break;
            case 3:
                homePageResident(resident, travauxArrayList, entravesArrayList);
                break;
            default:
                System.err.println("Votre choix est introuvable");
                travauxResident(resident, travauxArrayList, entravesArrayList);
                break;

        }


        clearScreen();
    }
    public void soumissionRequete(Resident resident, ArrayList<Travaux> travauxArrayList,
                                  ArrayList<Entraves> entravesArrayList) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.print("""
                //=============================================================\\\\
                ||           Soumission de requête pour un projet              ||
                ||                 Ville de Montréal                           ||
                ||-------------------------------------------------------------||
                ||                                                             ||
                ||   1) Nom du projet : _________________________________      ||
                ||                                                             ||
                ||   2) Localisation du projet : ________________________      ||
                ||                                                             ||
                ||   3) Type de projet :                                       ||
                ||      [ ] Réparation de routes                               ||
                ||      [ ] Réseau d'aqueduc                                   ||
                ||      [ ] Conduites de gaz                                   ||
                ||      [ ] Installation de lampadaires                        ||
                ||      [ ] Autre : ___________________________________        ||
                ||                                                             ||
                ||   4) Date de début souhaité : ____/____/______              ||
                ||                                                             ||
                ||   5) Détails supplémentaires : ________________________     ||
                ||                            ____________________________     ||
                ||                                                             ||
                ||-------------------------------------------------------------||
                ||                                                             ||
                ||   8) Envoyer la requête                                     ||
                ||   9) Annuler                                                ||
                ||                                                             ||
                \\\\=============================================================//
                        Votre choix :   """);
        choice = scanner.nextInt();
        clearScreen();
        switch (choice){
            case 8,9:
                homePageResident(resident, travauxArrayList, entravesArrayList);
                break;
            default:
                System.err.println("Votre choix est introuvable");
                soumissionRequete(resident, travauxArrayList, entravesArrayList);
        }
    }
    public void rechercheTravaux(Resident resident, ArrayList<Travaux> travauxArrayList,
                                 ArrayList<Entraves> entravesArrayList) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.print("""
                //==================================================\\\\
                ||               Recherche de travaux               ||
                ||                  Ville de Montréal               ||
                ||--------------------------------------------------||
                ||                                                  ||
                ||   1) Rechercher par titre du projet              ||
                ||                                                  ||
                ||   2) Rechercher par type de travaux              ||
                ||                                                  ||
                ||   3) Rechercher par quartier                     ||
                ||                                                  ||
                ||   4) Rechercher par rue                          ||
                ||                                                  ||
                ||--------------------------------------------------||
                ||                                                  ||
                ||   0) Retourner au menu principal                 ||
                ||                                                  ||
                \\\\==================================================//
                        Votre choix :   """);
        choice = scanner.nextInt();
        clearScreen();
        switch (choice){
            case 1, 2, 3, 4, 0:
                travauxResident(resident, travauxArrayList, entravesArrayList);
                break;
            default:
                System.err.println("Erreur de choix !\n");
                rechercheTravaux(resident, travauxArrayList, entravesArrayList);
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
    public void participation(Resident resident, ArrayList<Travaux> travauxArrayList,
                              ArrayList<Entraves> entravesArrayList) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.print("""
                //=============================================================================\\\\
                ||                   Gestion des travaux dans votre quartier                   ||
                ||                              Ville de Montréal                              ||
                ||-----------------------------------------------------------------------------||
                ||                                                                             ||
                ||   1) Indiquer vos plages horaires préférées pour les travaux                ||
                ||                                                                             ||
                ||   2) Consulter les préférences des autres résidents                         ||
                ||                                                                             ||
                ||   3) Partager votre avis sur les travaux terminés                           ||
                ||                                                                             ||
                ||-----------------------------------------------------------------------------||
                ||                                                                             ||
                ||   0) Retourner au menu principal                                            ||
                ||                                                                             ||
                \\\\=============================================================================//
                        Votre choix :   """);
        choice = scanner.nextInt();
        clearScreen();
        switch (choice){
            case 1,2,3,0:
                homePageResident(resident, travauxArrayList, entravesArrayList);
                break;
            default:
                System.err.println("Votre choix est introuvable");
                soumissionRequete(resident, travauxArrayList, entravesArrayList);
        }
    }
    public void exempleTravaux(Resident resident, ArrayList<Travaux> travauxArrayList,
                               ArrayList<Entraves> entravesArrayList) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("""
                //======================================================================\\\\
                ||                    Détails d'un chantier en cours                    ||
                ||                           Ville de Montréal                          ||
                ||----------------------------------------------------------------------||
                ||                                                                      ||
                ||   1) Titre du projet : Réparation des conduites d'eau                ||
                ||                                                                      ||
                ||   2) Type de travaux : Remplacement des conduites d'eau              ||
                ||                                                                      ||
                ||   3) Quartier affecté : Plateau-Mont-Royal                           ||
                ||                                                                      ||
                ||   4) Rue(s) affectée(s) : Avenue du Parc, Rue Rachel                 ||
                ||                                                                      ||
                ||   5) Date de début : 05/10/2024                                      ||
                ||                                                                      ||
                ||   6) Date de fin prévue : 20/12/2024                                 ||
                ||                                                                      ||
                ||   7) Horaire des travaux : Lundi - Vendredi, 7h à 17h                ||
                ||                                                                      ||
                ||   8) Intervenant responsable : Jean Tremblay                         ||
                ||                                                                      ||
                ||   9) Contact pour plus d'informations : travaux@montreal.ca          ||
                ||                                                                      ||
                ||----------------------------------------------------------------------||
                ||                                                                      ||
                ||   0) Retourner à la liste des travaux                                ||
                ||                                                                      ||
                \\\\======================================================================//
                        Votre choix :   """);
        choice = scanner.nextInt();
        clearScreen();
        if (choice == 0){
            homePageResident(resident, travauxArrayList, entravesArrayList);
        }else{
            System.err.println("Votre choix est introuvable!\n");
            exempleTravaux(resident, travauxArrayList, entravesArrayList);
        }
    }

}
