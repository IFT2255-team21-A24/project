package ca.umontreal.dir.ift2255.team21.cli;

import ca.umontreal.dir.ift2255.team21.accounts.Manager;
import ca.umontreal.dir.ift2255.team21.accounts.Resident;
import ca.umontreal.dir.ift2255.team21.apihandler.TransformAddress;
import ca.umontreal.dir.ift2255.team21.databasehandler.InsertData;
import ca.umontreal.dir.ift2255.team21.databasehandler.RequestsDB;
import ca.umontreal.dir.ift2255.team21.distancecalculator.CalculateDistance;
import ca.umontreal.dir.ift2255.team21.entraves.Entraves;
import ca.umontreal.dir.ift2255.team21.entraves.Travaux;
import ca.umontreal.dir.ift2255.team21.requests.Requests;
import ca.umontreal.dir.ift2255.team21.requests.RquestsIntervenant;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
                soumissionRequete(manager, travauxArrayList, entravesArrayList,"","",new String[]{"","",""},"");
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
        ||  1) Revenir à la page précédente                                                                       ||
        ||  2) Aller à la page suivante                                                                           ||
        ||  3) Revenir à l'accueil                                                                                ||
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
            case 3:
                homePageManager(manager, travauxArrayList, entravesArrayList);
                break;
            case 1:
                if (index > 0){
                    index--;
                    travauxManager(manager, travauxArrayList, entravesArrayList, index);
                }else {
                    System.err.println("On ne peut pas retourner plus en arrière.");
                    travauxManager(manager, travauxArrayList, entravesArrayList, index);
                }
                break;
            case 2:
                if (i<entravesArrayList.size()){
                    index++;
                    travauxManager(manager, travauxArrayList, entravesArrayList, index);
                }else {
                    System.err.println("Vous êtes rendu à la fin de la liste.");
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
                                  ArrayList<Entraves> entravesArrayList, String nom, String type, String[] croisement,
                                  String date) {
        Scanner input = new Scanner(System.in);
        Scanner keyboard = new Scanner(System.in);
        String format = "dd-MM-yyyy";

        int choice=0;
        String entete = """
                //=============================================================\\\\
                ||           Soumission de requête pour un projet              ||
                ||                 Ville de Montréal                           ||
                ||-------------------------------------------------------------||
                """;
        String footer = """
                ||-------------------------------------------------------------||
                ||   # Ici, choisissez le numéro du champ de text à modifier   ||
                ||   7) Soumettre la requête                                   ||
                ||   0) Annuler                                                ||
                ||                                                             ||
                \\\\===========================================================//
                        Votre choix :   """;
        String body= entete + "     Nom de projet : " + nom +"\n"+"     Type de projet : " + type + "\n" + "     Croisements :\n";
        for (int i = 0; i < croisement.length; i++) {
            body += croisement[i];
            body += "\n";
        }
        body+= "     Date début : " + date + "\n" + "     Status : " + "\n" + footer;

        System.out.print(body);

        try {
            choice = input.nextInt();
        }catch (Exception e) {
            System.err.println("Votre entrée est invalide!");
            soumissionRequete(manager, travauxArrayList, entravesArrayList, nom, type,croisement,date);
        }

        clearScreen();
        switch (choice) {
            case 1:
                String projet = "";
                System.out.print("Entrez le nom du projet : ");
                projet = keyboard.nextLine();
                if (projet.isBlank()){
                    System.err.println("Nom de projet vide!");
                }
                soumissionRequete(manager, travauxArrayList, entravesArrayList, projet, type,croisement,date);
                break;
            case 2:
                int choix=0;
                System.out.println("""
                =================================================================
                ||      Type de projet :                                       ||
                ||      [1] Réparation de routes                               ||
                ||      [2] Réseau d'aqueduc                                   ||
                ||      [3] Conduites de gaz                                   ||
                ||      [4] Installation de lampadaires                        ||
                ||      [5] Autre : ___________________________________        ||
                        """);
                try {
                    choix = input.nextInt();
                }catch (Exception e) {
                    System.err.println("Votre entrée est invalide!");
                    soumissionRequete(manager, travauxArrayList, entravesArrayList, nom, type,croisement,date);
                }
                String type_de_projet = "";
                switch (choix) {
                    case 1:
                        type_de_projet = "Réparation de routes";
                        break;
                    case 2:
                        type_de_projet="Réseau d'aqueduc";
                        break;
                    case 3:
                        type_de_projet = "Conduites de gaz";
                        break;
                    case 4:
                        type_de_projet="Installation de lampadaires";
                        break;
                    case 5:
                        System.out.print("Entrez le catégorie du projet : ");
                        type_de_projet = keyboard.nextLine();
                        if (type_de_projet.isBlank()){
                            System.err.println("Type de projet vide!");
                        }
                    default:
                        System.err.println("Votre choix est invalide!");
                }
                soumissionRequete(manager, travauxArrayList, entravesArrayList, nom, type_de_projet ,croisement,date);
                break;
            case 3:
                String affectes[] = new String[3];
                System.out.print("Entrez la route principale");
                affectes[0] = keyboard.nextLine();
                if (affectes[0].isBlank()){
                    System.err.println("Route principale vide!");
                }
                System.out.print("Entrez le premier croisement (seulement le nom de la route qui croise la route principale) :");
                affectes[1] = keyboard.nextLine();
                if (affectes[1].isBlank()){
                    System.err.println("Premier croisemente vide!");
                }
                System.out.print("Entrez le deuxième croisement (seulement le nom de la route qui croise la route principale) :");
                affectes[2] = keyboard.nextLine();
                if (affectes[2].isBlank()){
                    System.err.println("Deuxième croisemente vide!");
                }
                if ((affectes[0].isBlank() || affectes[1].isBlank() || affectes[2].isBlank())){
                    System.err.println("Il y a des données manquants");
                    affectes[0] = "";
                    affectes[1] = "";
                    affectes[2] = "";
                }
                soumissionRequete(manager, travauxArrayList, entravesArrayList, nom, type ,affectes,date);
                break;
            case 4:
                System.out.print("4) Date de début souhaité (DD-MM-YYYY) :   ");
                date = keyboard.nextLine();
                Date sqlDate = convertToSQLDate(date, format);
                if (sqlDate==null){
                    System.err.println("La date est dans un mauvais format!");
                    date = "";
                }
                soumissionRequete(manager, travauxArrayList, entravesArrayList, nom, type,croisement,date);
                break;
            case 7:
                if (!(nom.isBlank() ||  type.isBlank() ||  date.isBlank() ||  croisement[0].isBlank() ||  croisement[1].isBlank()
                || croisement[2].isBlank())){
                    RquestsIntervenant rquestsIntervenant = new RquestsIntervenant(nom,type,croisement,"En Cours",
                            convertToSQLDate(date, format));
                    InsertData.insertRequest(rquestsIntervenant, manager);
                }


            case 0:
                homePageManager(manager, travauxArrayList, entravesArrayList);
                break;
            default:
                System.err.println("Votre choix est introuvable");
                soumissionRequete(manager, travauxArrayList, entravesArrayList, nom, type,croisement,date);
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
            consulterRequete(manager, travauxArrayList, entravesArrayList, index);
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

    public static void clearScreen() {
        try {
            final String system = System.getProperty("os.name").toLowerCase();
            if (system.contains("windows")) {
                Runtime.getRuntime().exec("cls");
            }
        }catch (Exception e) {}
    }
    private static java.sql.Date convertToSQLDate(String date, String format) {
        try {
            // Définir le format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

            // Parser la chaîne en LocalDate
            LocalDate localDate = LocalDate.parse(date, formatter);

            // Convertir LocalDate en java.sql.Date
            return Date.valueOf(localDate);
        } catch (DateTimeParseException e) {
            // En cas d'erreur de parsing
            return null;
        }
    }
}
