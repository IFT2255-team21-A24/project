package ca.umontreal.dir.ift2255.team21.cli;
import ca.umontreal.dir.ift2255.team21.accounts.Account;
import ca.umontreal.dir.ift2255.team21.databasehandler.ConnectionCheck;

import java.util.*;
import java.lang.*;
import java.io.*;
public class Display {
    public void introScreen(){
        Scanner choice = new Scanner(System.in);
        int menuChoice = 0;

        System.out.println("""
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
                """);
        System.out.print("Entrez le numéro de votre choix: ");
        menuChoice = choice.nextInt();
        choice.nextLine();
        String os = System.getProperty("os.name").toLowerCase();
        System.out.println(os);
        try {
            if (os.startsWith("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }else if (os.startsWith("mac")) {
                new ProcessBuilder("open", "/c", "cls").inheritIO().start().waitFor();
            } else if (os.startsWith("linux")) {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        }catch (Exception e){
            System.err.println(e);
        }

        if (menuChoice != 1 && menuChoice != 2) {
            introScreen();
        }else{
            choice.close();
            switch (menuChoice) {
                case 1:
                    seConnecter();
                    break;
                case 2:

                    break;
            }
        }
    }
    public void seConnecter(){
        Scanner input = new Scanner(System.in);
        Account account = null;
        String username = "", password = "";
        System.out.println("+----------------------------+");
        System.out.println("|         CONNECTION         |");
        System.out.println("|    Travaux de Montreal     |");
        System.out.println("+----------------------------+");
        System.out.print("Username: ");
        username = input.nextLine();
        System.out.print("Password: ");
        password = input.nextLine();
        account = ConnectionCheck.checkForUser(username, password);
        if (account == null) {
            System.out.println();
        }


    }

}
