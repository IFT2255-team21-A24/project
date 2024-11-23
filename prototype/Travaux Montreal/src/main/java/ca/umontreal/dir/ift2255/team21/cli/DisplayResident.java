package ca.umontreal.dir.ift2255.team21.cli;

import ca.umontreal.dir.ift2255.team21.accounts.Resident;
import ca.umontreal.dir.ift2255.team21.apihandler.AddressVerificator;
import ca.umontreal.dir.ift2255.team21.apihandler.TransformAddress;
import ca.umontreal.dir.ift2255.team21.databasehandler.InsertData;
import ca.umontreal.dir.ift2255.team21.distancecalculator.CalculateDistance;
import ca.umontreal.dir.ift2255.team21.entraves.Entraves;
import ca.umontreal.dir.ift2255.team21.entraves.Travaux;
import ca.umontreal.dir.ift2255.team21.requests.Requests;

import java.util.ArrayList;
import java.util.Scanner;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        ||  4) Recherche de travaux                                                                               ||
        ||  5) Se déconnecter                                                                                     ||
        ||                                                                                                        ||
        \\\\========================================================================================================//
                                    Votre choix :   """;

        int choice;
        String affichage = entete;
        for (int i = 0; i < 3; i++) {
            if (i<entravesProches.size()) {
                affichage += entravesProches.get(i);
                affichage += "||--------------------------------------------------------------------------------------------------------||\n";
            }else break;
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
                rechercheTravaux(resident, travauxArrayList,entravesArrayList);
                break;
            case 6:
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
                body += "||-------------------------------------------------------------------------------------------------------||\n";
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
                if (index > 0){
                    index--;
                    travauxResident(resident, travauxArrayList, entravesArrayList, index);
                }else {
                    System.err.println("Vous êtes rendu à la fin de la liste.");
                    travauxResident(resident, travauxArrayList, entravesArrayList, index);
                }
                break;
            case 3:
                if (i<entravesArrayList.size()){
                    index++;
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
        Scanner dataentry = new Scanner(System.in);
        String name="", type="", address="";
        String format = "dd-MM-yyyy";
        String date="";
        Date date1 = null;
        double longitude = 0, latitude = 0;
        int choice;
        System.out.print("""
        //========================================================================================================\\\\
        ||                                   Soumission de requête pour un projet                                 ||
        ||                                           Ville de Montréal                                            ||
        ||--------------------------------------------------------------------------------------------------------||
        ||                                    Sélectionnez les champs à modifier                                  ||
        ||--------------------------------------------------------------------------------------------------------||
        ||   1) Nom du projet : __________________________________________                                        ||
        ||                                                                                                        ||
        ||   2) Localisation du projet : __________________________________                                       ||
        ||                                                                                                        ||
        ||   3) Type de projet :                                                                                  ||
        ||      [ ] Réparation de routes                                                                          ||
        ||      [ ] Réseau d'aqueduc                                                                              ||
        ||      [ ] Conduites de gaz                                                                              ||
        ||      [ ] Installation de lampadaires                                                                   ||
        ||                                                                                                        ||
        ||   4) Date de début souhaité : ____/____/___________                                                    ||
        ||                                                                                                        ||
        ||--------------------------------------------------------------------------------------------------------||
        ||                                                                                                        ||
        ||   8) Envoyer la requête                                                                                ||
        ||   9) Annuler                                                                                           ||
        ||                                                                                                        ||
        \\\\========================================================================================================//""");
        do {
            System.out.print("Votre choix :   ");
            choice = scanner.nextInt();
            switch (choice){
                case 1:
                    System.out.print("1) Nom du projet : ");
                    name = dataentry.nextLine();
                    break;
                case 2:
                    System.out.print("2) localisation du projet (addresse) : ");
                    name = dataentry.nextLine();
                    if (!AddressVerificator.verifyAddress(address)){
                        address = "";
                        System.err.println("L'addresse est mauvaise ou introuvable.");
                    }else {
                        TransformAddress transformAddress = new TransformAddress();
                        String[] parts = transformAddress.transform(address).split(",");
                        // Convertir chaque partie en double
                        latitude = Double.parseDouble(parts[0]);
                        longitude = Double.parseDouble(parts[1]);
                    }
                    break;
                case 3:
                    System.out.print("""
        3) Type de projet :
          1-  [ ] Réparation de routes
          2-  [ ] Réseau d'aqueduc
          3-  [ ] Conduites de gaz
          4-  [ ] Installation de lampadaires
          
          Choix :: """);
                    int raison = scanner.nextInt();
                    switch (raison){
                        case 1:
                            type = "Réparation de routes";
                            break;
                        case 2:
                            type = "Réseau d'aqueduc";
                            break;
                        case 3:
                            type = "Conduites de gaz";
                            break;
                        case 4:
                            type = "Installation de lampadaires";
                            break;
                        default:
                            System.err.println("Choix non trouvable!");
                            break;
                    }
                    break;
                case 4:
                    System.out.print("4) Date de début souhaité (DD-MM-YYYY) :   ");
                    date = dataentry.nextLine();
                    Date sqlDate = convertToSQLDate(date, format);
                    if (sqlDate==null){
                        System.err.println("La date est dans un mauvais format!");
                        date = "";
                    }else {
                        date1 = sqlDate;
                    }
                    break;
                case 8:
                    if ((name.equals("")||date.equals("")||type.equals("")||address.equals(""))) {
                    System.err.println("Le tableau n'est pas rempli, voici vos données:\n" + name + "\n"+ date + "\n"
                            + type + "\n"+ address + "\n");
                }else {
                        Requests requests = new Requests(name, type, address, latitude, longitude, date1);
                        InsertData.insertRequest(requests);
                        homePageResident(resident, travauxArrayList,entravesArrayList);
                    }

                    break;
                case 9:
                    homePageResident(resident, travauxArrayList, entravesArrayList);
                    break;
                default:
                    System.err.println("Votre choix est introuvable");
                    soumissionRequete(resident, travauxArrayList, entravesArrayList);
            }
        }while (true);
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
                ||---------Cette partie est en développement--------||
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
            case 1, 2, 3, 4:
                travauxResident(resident, travauxArrayList, entravesArrayList, 0);
                break;
            case 0:
                homePageResident(resident, travauxArrayList, entravesArrayList);
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
                ||----------------------Cette partie est en développement----------------------||
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
    public static java.sql.Date convertToSQLDate(String date, String format) {
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
