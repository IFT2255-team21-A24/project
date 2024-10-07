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

        System.out.print("\033[H\033[2J");
        System.out.flush();

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
        input.close();
        account = ConnectionCheck.checkForUser(username, password);
        if (account == null) {
            System.out.println("pas de donnée");
        }else{

        }

    }

    public void homePageResident() {
        Scanner input = new Scanner(System.in);
        System.out.println("""
                //======================================================\\\\
                ||           Nouveautés sur les constructions           ||
                ||                 Dans votre régions :                 ||
                ||------------------------------------------------------||
                ||                                                      ||
                ||                    NOTIFICATIONS:                    ||
                ||                                                      ||
                ||    La requête que vous avez dépose est : Acceptée    ||
                ||              Date de début : 19-10-2024              ||
                ||                                                      ||
                ||------------------------------------------------------||
                ||                                                      ||
                ||              Rue Sainte-Catherine Est :              ||
                ||      Objectif : Réparer l'aqueduc au croisement      ||
                ||                 Sainte-Catherine Est / Berri.        ||
                ||            Date de fin prévu : 23-01-2025            ||
                ||                                                      ||
                ||                    Rue Durocher :                    ||
                ||       Objectif : Réparation des tuyaux de gaz.       ||
                ||            Date de fin prévu : 03-11-2024            ||
                ||                                                      ||
                ||------------------------------------------------------||
                ||  1) Consultation des travaux en cours/à venir.       ||
                ||  2) Soumission de requête                            ||
                ||  3) Planification participative                      ||
                ||  4) Signalisation de problème                        ||
                ||                                                      ||
                \\\\======================================================//
                        Votre choix :""");
    }

}
