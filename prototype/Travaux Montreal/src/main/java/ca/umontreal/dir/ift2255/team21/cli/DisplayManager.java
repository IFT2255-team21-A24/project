package ca.umontreal.dir.ift2255.team21.cli;

import ca.umontreal.dir.ift2255.team21.accounts.Manager;

import java.util.Scanner;

public class DisplayManager {
    public void homePageManager(Manager manager) {
        Scanner input = new Scanner(System.in);
        int choice;
        System.out.print("""
                //======================================================\\\\
                ||        Informations sur vos chantiers en cours       ||
                ||                 Région de Montréal :                 ||
                ||------------------------------------------------------||
                ||                                                      ||
                ||                    NOTIFICATIONS:                    ||
                ||                                                      ||
                ||    Le projet de construction que vous dirigez :      ||
                ||                   Accepté                            ||
                ||              Date de début : 19-10-2024              ||
                ||                                                      ||
                ||------------------------------------------------------||
                ||                                                      ||
                ||                Projets en cours :                    ||
                ||                                                      ||
                ||           1) Pont Jacques-Cartier :                  ||
                ||  Objectif : Réparation du tablier principal.         ||
                ||            Date de fin prévue : 15-12-2024           ||
                ||                                                      ||
                ||           2) Boulevard René-Lévesque :               ||
                ||  Objectif : Installation de nouveaux lampadaires     ||
                ||            Date de fin prévue : 08-11-2024           ||
                ||                                                      ||
                ||           3) Rue Sherbrooke Ouest :                  ||
                ||  Objectif : Réfection de la chaussée                 ||
                ||            Date de fin prévue : 25-10-2024           ||
                ||                                                      ||
                ||------------------------------------------------------||
                ||  1) Consultation des requêtes de travail             ||
                ||  2) Soumettre une nouvelle requête                   ||
                ||  4) Gestion des équipes et des ressources            ||
                ||  5) Se déconnecter                                   ||
                ||                                                      ||
                \\\\======================================================//
                        Votre choix :   """);

        choice = input.nextInt();

        clearScreen();
    }
    public void travauxManager(Manager manager) {
        System.out.print("""
                //================================================================\\\\
                ||           Travaux en cours/à venir dans votre région           ||
                ||                      Ville de Montréal                         ||
                ||----------------------------------------------------------------||
                ||                                                                ||
                ||                        TRAVAUX EN COURS:                       ||
                ||                                                                ||
                ||  1 - Rue Sainte-Catherine Est :                                ||
                ||     Objectif : Réparer l'aqueduc au croisement                 ||
                ||                Sainte-Catherine Est / Berri                    ||
                ||     Date de début : 10-10-2024                                 ||
                ||     Date de fin prévue : 23-01-2025                            ||
                ||                                                                ||
                ||  2 - Boulevard Saint-Laurent :                                 ||
                ||     Objectif : Réfection de la chaussée.                       ||
                ||     Date de début : 01-09-2024                                 ||
                ||     Date de fin prévue : 20-11-2024                            ||
                ||                                                                ||
                ||----------------------------------------------------------------||
                ||                                                                ||
                ||                    TRAVAUX À VENIR:                            ||
                ||                                                                ||
                ||  1 - Avenue du Parc :                                          ||
                ||     Objectif : Remplacement des conduites d'eau.               ||
                ||     Date de début : 05-11-2024                                 ||
                ||     Date de fin prévue : 15-12-2024                            ||
                ||                                                                ||
                ||  2 - Rue Notre-Dame Ouest :                                    ||
                ||     Objectif : Installation de nouvelles pistes cyclables.     ||
                ||     Date de début : 20-11-2024                                 ||
                ||     Date de fin prévue : 15-03-2025                            ||
                ||                                                                ||
                ||----------------------------------------------------------------||
                ||                                                                ||
                ||   1) Plus de détails sur un projet                             ||
                ||   2) Modifier un projet existant                               ||
                ||   4) Revenir à l'accueil                                       ||
                ||                                                                ||
                \\\\================================================================//
                        Votre choix :   """);

        clearScreen();
    }

    public void soumissionRequete(Manager manager) {
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
                ||   4) Quartiers affectés :                                   ||
                ||      [ ] Centre-Ville                                       ||
                ||      [ ] Plateau-Mont-Royal                                 ||
                ||      [ ] Rosemont                                           ||
                ||      [ ] Outremont                                          ||
                ||      [ ] Hochelaga-Maisonneuve                              ||
                ||      [ ] Autre : __________________________________         ||
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
