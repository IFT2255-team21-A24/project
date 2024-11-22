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
                travauxResident(resident,travauxArrayList,entravesArrayList, 0);
                break;
            case 2:
                entravesResident(resident,travauxArrayList,entravesArrayList, 0);
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
                                ArrayList<Entraves> entravesArrayList, int index) {
        Scanner scanner = new Scanner(System.in);
        int choice=0;
        String entete = """
        //========================================================================================================\\\\
        ||                                     Nouveautés sur les constructions                                   ||
        ||                                          Dans votre région :                                           ||
        ||--------------------------------------------------------------------------------------------------------||
        ||--------------------------------------------------------------------------------------------------------||
        """;
        String footer = """
        ||--------------------------------------------------------------------------------------------------------||
        ||                                                                                                        ||
        ||  1) Revenir à l'accueil                                                                                ||
        ||  2) Revenir à la page précédente                                                                       ||
        ||  3) Aller à la page suivante                                                                           ||
        ||                                                                                                        ||
        \\\\========================================================================================================//
                                    Votre choix :   """;
        String body= entete;
        int i  = index*7;
        int borne = (index+1)*7;
        for (; i < borne; i++) {
            if (i<entravesArrayList.size()) {
                body += travauxArrayList.get(i);
                body += "||--------------------------------------------------------------------------------------------------------||\n";
            }else break;
        }
        body+=footer;
        System.out.print(body);
        try {
            choice = scanner.nextInt();
        }catch (Exception e) {
            System.err.println("Votre entrée est invalide!");
            travauxResident(resident, travauxArrayList, entravesArrayList, index);
        }
        clearScreen();
        switch (choice){
            case 1:
                homePageResident(resident, travauxArrayList, entravesArrayList);
                break;
            case 2:
                if (i<entravesArrayList.size()){
                    index++;
                    travauxResident(resident, travauxArrayList, entravesArrayList, index);
                }else {
                    System.err.println("Vous êtes rendu à la fin de la liste.");
                    travauxResident(resident, travauxArrayList, entravesArrayList, index);
                }
                break;
            case 3:
                if (index > 0){
                    index--;
                    travauxResident(resident, travauxArrayList, entravesArrayList, index);
                }else {
                    System.err.println("On ne peut pas retourner plus en arrière.");
                    travauxResident(resident, travauxArrayList, entravesArrayList, index);
                }
                break;
            default:
                System.err.println("Votre choix est introuvable");
                travauxResident(resident, travauxArrayList, entravesArrayList, index);
                break;

        }


        clearScreen();
    }
    public void entravesResident(Resident resident, ArrayList<Travaux> travauxArrayList,
                                ArrayList<Entraves> entravesArrayList, int index) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        String entete = """
        //========================================================================================================\\\\
        ||                                     Nouveautés sur les constructions                                   ||
        ||                                          Dans votre région :                                           ||
        ||--------------------------------------------------------------------------------------------------------||
        ||--------------------------------------------------------------------------------------------------------||
        """;
        String footer = """
        ||--------------------------------------------------------------------------------------------------------||
        ||                                                                                                        ||
        ||  1) Revenir à l'accueil                                                                                ||
        ||  2) Revenir à la page précédente                                                                       ||
        ||  3) Aller à la page suivante                                                                           ||
        ||                                                                                                        ||
        \\\\========================================================================================================//
                                    Votre choix :   """;
        String body= entete;
        int i  = index*7;
        int borne = (index+1)*7;
        for (; i < borne; i++) {
            if (i<entravesArrayList.size()) {
                body += entravesArrayList.get(i);
                body += "||--------------------------------------------------------------------------------------------------------||\n";
            }else break;
        }
        body+=footer;
        System.out.print(body);
        choice = scanner.nextInt();
        clearScreen();
        switch (choice){
            case 1:
                homePageResident(resident, travauxArrayList, entravesArrayList);
                break;
            case 2:
                if (i<entravesArrayList.size()){
                    index++;
                    entravesResident(resident, travauxArrayList, entravesArrayList, index);
                }else {
                    System.err.println("Vous êtes rendu à la fin de la liste.");
                    entravesResident(resident, travauxArrayList, entravesArrayList, index);
                }
                break;
            case 3:
                if (index > 0){
                    index--;
                    entravesResident(resident, travauxArrayList, entravesArrayList, index);
                }else {
                    System.err.println("On ne peut pas retourner plus en arrière.");
                    entravesResident(resident, travauxArrayList, entravesArrayList, index);
                }
                break;
            default:
                System.err.println("Votre choix est introuvable");
                entravesResident(resident, travauxArrayList, entravesArrayList, index);
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
                travauxResident(resident, travauxArrayList, entravesArrayList, 0);
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
