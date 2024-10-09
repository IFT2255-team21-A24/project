package ca.umontreal.dir.ift2255.team21.cli;

import ca.umontreal.dir.ift2255.team21.accounts.Resident;

import java.util.Scanner;

public class DisplayResident {
    public void homePageResident(Resident resident) {
        Scanner input = new Scanner(System.in);
        int choice;
        System.out.print("""
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
                ||  5) Se déconnecter                                   ||
                ||                                                      ||
                \\\\======================================================//
                        Votre choix :   """);
        choice = input.nextInt();
        clearScreen();
        switch (choice){
            case 1:
                travauxResident(resident);
                break;
            case 2:
            case 3:
            case 4:
        }


    }
    public void travauxResident(Resident resident) {
        System.out.print("""
                //================================================================\\\\
                ||           Travaux en cours/à venir dans votre région           ||
                ||                      Ville de Montréal                         ||
                ||----------------------------------------------------------------||
                ||                                                                ||
                ||                        TRAVAUX EN COURS:                       ||
                ||                                                                ||
                ||  1) Rue Sainte-Catherine Est :                                 ||
                ||     Objectif : Réparer l'aqueduc au croisement                 ||
                ||                Sainte-Catherine Est / Berri                    ||
                ||     Date de début : 10-10-2024                                 ||
                ||     Date de fin prévue : 23-01-2025                            ||
                ||                                                                ||
                ||  2) Boulevard Saint-Laurent :                                  ||
                ||     Objectif : Réfection de la chaussée.                       ||
                ||     Date de début : 01-09-2024                                 ||
                ||     Date de fin prévue : 20-11-2024                            ||
                ||                                                                ||
                ||----------------------------------------------------------------||
                ||                                                                ||
                ||                    TRAVAUX À VENIR:                            ||
                ||                                                                ||
                ||  1) Avenue du Parc :                                           ||
                ||     Objectif : Remplacement des conduites d'eau.               ||
                ||     Date de début : 05-11-2024                                 ||
                ||     Date de fin prévue : 15-12-2024                            ||
                ||                                                                ||
                ||  2) Rue Notre-Dame Ouest :                                     ||
                ||     Objectif : Installation de nouvelles pistes cyclables.     ||
                ||     Date de début : 20-11-2024                                 ||
                ||     Date de fin prévue : 15-03-2025                            ||
                ||                                                                ||
                ||----------------------------------------------------------------||
                ||                                                                ||
                ||   1) Plus de détails sur un projet                             ||
                ||   2) Proposer un ajustement pour un projet                     ||
                ||   3) Signaler une problème                                     ||
                ||   4) Revenir à l'accueil                                       ||
                ||                                                                ||
                \\\\================================================================//
                        Votre choix :   """);


        clearScreen();
    }
    public void soumissionRequete(Resident resident) {
        System.out.println("""
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
                ||   [Envoyer le projet]   [Annuler]                           ||
                ||                                                             ||
                \\\\===========================================================//
                        Votre choix :   """);


        clearScreen();
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
