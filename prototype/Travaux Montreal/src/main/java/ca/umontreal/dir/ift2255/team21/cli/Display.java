package ca.umontreal.dir.ift2255.team21.cli;
import ca.umontreal.dir.ift2255.team21.accounts.Account;
import ca.umontreal.dir.ift2255.team21.accounts.Manager;
import ca.umontreal.dir.ift2255.team21.accounts.Resident;
import ca.umontreal.dir.ift2255.team21.databasehandler.ConnectionCheck;
import ca.umontreal.dir.ift2255.team21.databasehandler.EntravesDB;
import ca.umontreal.dir.ift2255.team21.databasehandler.TravauxDB;
import ca.umontreal.dir.ift2255.team21.entraves.Entraves;
import ca.umontreal.dir.ift2255.team21.entraves.Travaux;

import java.util.*;
import java.lang.*;

/**
 * !! NE PAS EFFACER !!
 *
 * DATA pour test
 * Username : Password
 * luc : test
 * marie : Marie
 * jeanR : Roy_1975
 */

public class Display {
    public void introScreen(){
        Scanner choice = new Scanner(System.in);
        int menuChoice = 0;

        System.out.print("""
                *********************************
                **                             **
                **   $$\\      $$\\ $$$$$$$$\\    **
                **   $$$\\    $$$ |\\__$$  __|   **
                **   $$$$\\  $$$$ |   $$ |      **
                **   $$\\$$\\$$ $$ |   $$ |      **
                **   $$ \\$$$  $$ |   $$ |      **
                **   $$ |\\$  /$$ |   $$ |      **
                **   $$ | \\_/ $$ |   $$ |      **
                **   \\__|     \\__|   \\__|      **
                **                             **
                **    Bienvenue sur le site    **
                **     Travaux de Montreal     **
                **                             **
                *********************************
                **                             **
                **    Choisissez un option:    **
                **      1) Se Connecter        **
                **      2)  S'inscrire         **
                **                             **
                *********************************
                      Entrez votre choix :""");

        menuChoice = choice.nextInt();

        clearScreen();

        if (menuChoice != 1 && menuChoice != 2) {
            System.err.println("Erreur d'entrée");
            introScreen();
        }else if(menuChoice == 1){
            seConnecter();
        }
    }
    public void seConnecter(){
        Scanner input = new Scanner(System.in);
        Account account = null;
        String username;
        String password;
        System.out.println("+----------------------------+");
        System.out.println("|         CONNECTION         |");
        System.out.println("|    Travaux de Montreal     |");
        System.out.println("+----------------------------+");
        System.out.print("   Username: ");
        username = input.nextLine();
        System.out.print("   Mot de passe: ");
        password = input.nextLine();

        account = ConnectionCheck.checkForUser(username, password);
        if (account == null) {
            System.out.println("Utilisateur non trouvé ou mot de passe incorrect.\n");
            erreurConnection();
        }else{
            clearScreen();
            if (account instanceof Resident) {
                ArrayList<Entraves> entravesArrayList = EntravesDB.retrieveAllEntraves();
                ArrayList<Travaux> travauxArrayList = TravauxDB.retrieveTravaux();
                DisplayResident displayResident = new DisplayResident();
                displayResident.homePageResident((Resident)account, travauxArrayList, entravesArrayList);
            }else if (account instanceof Manager) {
                DisplayManager displayManager = new DisplayManager();
                displayManager.homePageManager((Manager) account);

            }

        }

    }
    public void erreurConnection(){
        Scanner input = new Scanner(System.in);
        String choice = "";
        System.out.println("Voulez-vous vous réessayer (Y/N) ?");
        choice = input.nextLine();
        if (choice.equalsIgnoreCase("Y")) {
            seConnecter();
        }else if (choice.equalsIgnoreCase("N")) {
            introScreen();
        }else {
            System.err.println("Erreur de choix.\n");
            erreurConnection();
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
