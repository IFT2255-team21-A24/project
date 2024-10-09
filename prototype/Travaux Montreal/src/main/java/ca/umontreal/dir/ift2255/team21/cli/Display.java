package ca.umontreal.dir.ift2255.team21.cli;
import ca.umontreal.dir.ift2255.team21.accounts.Account;
import ca.umontreal.dir.ift2255.team21.accounts.Manager;
import ca.umontreal.dir.ift2255.team21.accounts.Resident;
import ca.umontreal.dir.ift2255.team21.databasehandler.ConnectionCheck;

import java.util.*;
import java.lang.*;
public class Display {
    public void introScreen() throws Exception{
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
            System.out.println("pas de donnée");
        }else{
            clearScreen();
            if (account instanceof Resident) {
                DisplayResident displayResident = new DisplayResident();
                displayResident.homePageResident((Resident)account);
            }else if (account instanceof Manager) {
                DisplayManager displayManager = new DisplayManager();
                displayManager.homePageManager((Manager) account);

            }

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
