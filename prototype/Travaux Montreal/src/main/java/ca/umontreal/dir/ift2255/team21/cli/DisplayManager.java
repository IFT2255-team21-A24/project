package ca.umontreal.dir.ift2255.team21.cli;

import ca.umontreal.dir.ift2255.team21.accounts.Manager;

import java.util.Scanner;

public class DisplayManager {
    public void homePageManager(Manager manager) {
        Scanner input = new Scanner(System.in);
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
                ||                                                      ||
                \\\\======================================================//
                        Votre choix :   """);
    }
    public void travauxManager(Manager manager) {
        System.out.println("""
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
                ||   2) Modifier un projet existant                               ||
                ||   4) Revenir à l'accueil                                       ||
                ||                                                                ||
                \\\\================================================================//
                        Votre choix :   """);
    }
}
