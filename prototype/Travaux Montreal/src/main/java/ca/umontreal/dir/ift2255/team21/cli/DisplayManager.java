package ca.umontreal.dir.ift2255.team21.cli;

import ca.umontreal.dir.ift2255.team21.accounts.Manager;
import ca.umontreal.dir.ift2255.team21.accounts.Resident;
import ca.umontreal.dir.ift2255.team21.apihandler.TransformAddress;
import ca.umontreal.dir.ift2255.team21.databasehandler.RequestsDB;
import ca.umontreal.dir.ift2255.team21.distancecalculator.CalculateDistance;
import ca.umontreal.dir.ift2255.team21.entraves.Entraves;
import ca.umontreal.dir.ift2255.team21.entraves.Travaux;
import ca.umontreal.dir.ift2255.team21.requests.Requests;

import java.util.ArrayList;
import java.util.Scanner;



public class DisplayManager {
    public void homePageManager(Manager manager,  ArrayList<Travaux> travauxArrayList,
                                ArrayList<Entraves> entravesArrayList) {
        Scanner input = new Scanner(System.in);
        int choice=0;
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
        String addrCoordinate = transformAddress.transform(manager.getResidential_adress());
        ArrayList<Entraves> entravesProches = CalculateDistance.listerEntravesProches(entravesArrayList, addrCoordinate);
        String footer = """
        ||--------------------------------------------------------------------------------------------------------||
        ||  1) Consultation des travaux en cours/à venir.                                                         ||
        ||  2) Consultation des entraves.                                                                         ||
        ||  3) Soumettre une nouvelle requête                                                                     ||
        ||  4) Consulter la liste des requêtes                                                                    ||
        ||  5) Se déconnecter                                                                                     ||
        ||                                                                                                        ||
        \\\\========================================================================================================//
                                    Votre choix :   """;
        String affichage = entete;
        for (Entraves travaux : entravesProches) {
            affichage += travaux.toString();
            affichage += "||--------------------------------------------------------------------------------------------------------||\n";
        }
        affichage += footer;
        System.out.print(affichage);
        try {
            choice = input.nextInt();
        }catch (Exception e) {
            System.err.println("Votre entrée est invalide!");
            homePageManager(manager, travauxArrayList, entravesArrayList);
        }
        clearScreen();

        switch (choice){
            case 1:
                travauxManager(manager, travauxArrayList, entravesArrayList,0);
                break;
            case 3:
                soumissionRequete(manager, travauxArrayList, entravesArrayList);
                break;
            case 4:
                consulterRequete(manager, travauxArrayList, entravesArrayList,0);
                break;
            case 5:
                manager = null;
                break;
            default:
                System.err.println("Choix indisponible.\n");
                homePageManager(manager, travauxArrayList, entravesArrayList);
                break;
        }
    }
    public void entravesManager(Manager manager, ArrayList<Travaux> travauxArrayList,
                                 ArrayList<Entraves> entravesArrayList, int index) {
        Scanner scanner = new Scanner(System.in);
        int choice=0;
        String entete = """
        //========================================================================================================\\\\
        ||                                     Nouveautés sur les entraves                                        ||
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
        try {
            choice = scanner.nextInt();
        }catch (Exception e) {
            System.err.println("Votre entrée est invalide!");
            entravesManager(manager, travauxArrayList, entravesArrayList, index);
        }
        clearScreen();
        switch (choice){
            case 1:
                homePageManager(manager, travauxArrayList, entravesArrayList);
                break;
            case 2:
                if (i<entravesArrayList.size()){
                    index++;
                    entravesManager(manager, travauxArrayList, entravesArrayList, index);
                }else {
                    System.err.println("Vous êtes rendu à la fin de la liste.");
                    entravesManager(manager, travauxArrayList, entravesArrayList, index);
                }
                break;
            case 3:
                if (index > 0){
                    index--;
                    entravesManager(manager, travauxArrayList, entravesArrayList, index);
                }else {
                    System.err.println("On ne peut pas retourner plus en arrière.");
                    entravesManager(manager, travauxArrayList, entravesArrayList, index);
                }
                break;
            default:
                System.err.println("Votre choix est introuvable");
                entravesManager(manager, travauxArrayList, entravesArrayList, index);
                break;

        }


        clearScreen();
    }
    public void travauxManager(Manager manager, ArrayList<Travaux> travauxArrayList,
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
        ||  1) Plus de détails sur un projet                                                                      ||
        ||  2) Revenir à la page précédente                                                                       ||
        ||  3) Aller à la page suivante                                                                           ||
        ||  4) Revenir à l'accueil                                                                                ||
        ||                                                                                                        ||
        \\\\========================================================================================================//
                        Votre choix :   """;
        String body= entete;
        int i  = index*7;
        int borne = (index+1)*7;
        for (; i < borne; i++) {
            if (i<entravesArrayList.size()) {
                body += travauxArrayList.get(i);
                body += "||-------------------------------------------------------------------------------------------------------||\n";
            }else break;
        }
        body+=footer;
        System.out.print(body);
        try {
            choice = scanner.nextInt();
        }catch (Exception e) {
            System.err.println("Votre entrée est invalide!");
            travauxManager(manager, travauxArrayList, entravesArrayList, index);
        }

        switch (choice) {
            case 1:
                int id;
                System.out.print("Entrez l'index du projet : ");
                id = scanner.nextInt();
                exempleTravail(manager, travauxArrayList,entravesArrayList, id);
                break;
            case 2:
                if (index > 0){
                    index--;
                    travauxManager(manager, travauxArrayList, entravesArrayList, index);
                }else {
                    System.err.println("Vous êtes rendu à la fin de la liste.");
                    travauxManager(manager, travauxArrayList, entravesArrayList, index);
                }
                break;
            case 3:
                if (i<entravesArrayList.size()){
                    index++;
                    travauxManager(manager, travauxArrayList, entravesArrayList, index);
                }else {
                    System.err.println("On ne peut pas retourner plus en arrière.");
                    travauxManager(manager, travauxArrayList, entravesArrayList, index);
                }
                break;
            default:
                System.err.println("Votre choix est introuvable");
                travauxManager(manager, travauxArrayList, entravesArrayList, index);
                break;
        }

        clearScreen();
    }

    public void soumissionRequete(Manager manager,  ArrayList<Travaux> travauxArrayList,
                                  ArrayList<Entraves> entravesArrayList) {
        Scanner input = new Scanner(System.in);
        int choice=0;
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

        try {
            choice = input.nextInt();
        }catch (Exception e) {
            System.err.println("Votre entrée est invalide!");
            soumissionRequete(manager, travauxArrayList, entravesArrayList);
        }

        clearScreen();
        switch (choice) {
            case 0,1,2,3,4,5,6,7,8,9:
                homePageManager(manager, travauxArrayList, entravesArrayList);
                break;
            default:
                System.err.println("Votre choix est introuvable");
                soumissionRequete(manager, travauxArrayList, entravesArrayList);
                break;
        }
    }

    public void consulterRequete(Manager manager,  ArrayList<Travaux> travauxArrayList,
                                 ArrayList<Entraves> entravesArrayList, int index) {
        Scanner input = new Scanner(System.in);
        ArrayList<Requests> requestsArrayList = RequestsDB.retrieveRequests();
        int choice=0;
        String entete = """
        //========================================================================================================\\\\
        ||                               Liste des requêtes soumis par les clients :                              ||
        ||--------------------------------------------------------------------------------------------------------||
        ||--------------------------------------------------------------------------------------------------------||
        """;
        String footer = """
        ||--------------------------------------------------------------------------------------------------------||
        ||                                                                                                        ||
        ||  1) Revenir à la page précédente                                                                       ||
        ||  2) Aller à la page suivante                                                                           ||
        ||  3) Revenir à l'accueil                                                                                ||
        ||                                                                                                        ||
        \\\\========================================================================================================//
                        Votre choix :   """;


        String body=entete;
        int i  = index*7;
        int borne = (index+1)*7;
        for (; i < borne; i++) {
            if (i<requestsArrayList.size()) {
                body += requestsArrayList.get(i);
                body += "||-------------------------------------------------------------------------------------------------------||\n";
            }else break;
        }
        body += footer;
        System.out.print(body);
        try {
            choice = input.nextInt();
        }catch (Exception e) {
            System.err.println("Votre entrée est invalide!");
            soumissionRequete(manager, travauxArrayList, entravesArrayList);
        }
        clearScreen();
        switch (choice) {
            case 1:
                if (index > 0){
                    index--;
                    consulterRequete(manager, travauxArrayList, entravesArrayList, index);
                }else {
                    System.err.println("Vous êtes rendu à la fin de la liste.");
                    consulterRequete(manager, travauxArrayList, entravesArrayList, index);
                }
                break;
            case 2:
                if (i<entravesArrayList.size()){
                    index++;
                    consulterRequete(manager, travauxArrayList, entravesArrayList, index);
                }else {
                    System.err.println("On ne peut pas retourner plus en arrière.");
                    consulterRequete(manager, travauxArrayList, entravesArrayList, index);
                }
                break;
            case 3:
                homePageManager(manager, travauxArrayList, entravesArrayList);
                break;
            default:
                System.err.println("Votre choix est introuvable");
                travauxManager(manager, travauxArrayList, entravesArrayList, index);
                break;
        }
    }

    public void exempleTravail(Manager manager,  ArrayList<Travaux> travauxArrayList,
                               ArrayList<Entraves> entravesArrayList, int index) {
        Scanner input = new Scanner(System.in);
        int choice=0;
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
        try {
            choice = input.nextInt();
        }catch (Exception e) {
            System.err.println("Votre entrée est invalide!");
            exempleTravail(manager, travauxArrayList, entravesArrayList, index);
        }
        clearScreen();
        switch (choice) {
            case 0,1,2,3,4,5,6,7,8,9:
                homePageManager(manager, travauxArrayList,entravesArrayList);
                break;
            default:
                System.err.println("Votre choix est introuvable");
                exempleTravail(manager, travauxArrayList,entravesArrayList, index);
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
