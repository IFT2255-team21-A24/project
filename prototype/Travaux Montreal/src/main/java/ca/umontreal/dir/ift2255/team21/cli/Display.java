package ca.umontreal.dir.ift2255.team21.cli;
import ca.umontreal.dir.ift2255.team21.accounts.Account;
import ca.umontreal.dir.ift2255.team21.databasehandler.ConnectionCheck;

import java.util.*;
import java.lang.*;
import java.io.*;
public class Display {
    public void introScreen() throws Exception{
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
                       Entrez votre choix:
                """);

        menuChoice = choice.nextInt();

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
        System.out.print("Username: ");
        username = input.nextLine();
        System.out.print("Username: ");
        password = input.nextLine();
        input.close();
        account = ConnectionCheck.checkForUser(username, password);
        if (account == null) {
            System.out.println("pas de donnée");
        }else System.out.println(account);


    }

}
