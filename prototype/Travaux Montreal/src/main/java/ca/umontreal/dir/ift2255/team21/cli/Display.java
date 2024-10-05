package ca.umontreal.dir.ift2255.team21.cli;
import java.util.*;
import java.lang.*;
import java.io.*;
public class Display {
    public int introScreen(){
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
        if (menuChoice != 1 && menuChoice != 2) {
            menuChoice = introScreen();
        }
        choice.close();
        return menuChoice;
    }
    public boolean seConnecter(){
        Scanner input = new Scanner(System.in);
        String username = "", password = "";
        System.out.println("+----------------------------+");
        System.out.println("|         CONNECTION         |");
        System.out.println("|    Travaux de Montreal     |");
        System.out.println("+----------------------------+");
        System.out.print("Username: ");
        username = input.nextLine();
        System.out.print("Password: ");
        password = input.nextLine();
        return true;

    }

}
