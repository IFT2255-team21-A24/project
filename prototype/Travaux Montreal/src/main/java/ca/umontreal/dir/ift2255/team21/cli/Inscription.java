package ca.umontreal.dir.ift2255.team21.cli;

import ca.umontreal.dir.ift2255.team21.apihandler.AddressVerificator;
import ca.umontreal.dir.ift2255.team21.databasehandler.InsertData;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Inscription {
    public static void Inscrire(){
        String nomFamille="";
        String prenom="";
        String adresse="";
        LocalDate dateNaissance=null;
        String telephone="";
        String email="";
        String password="";
        int cityNumber = -1;
        System.out.println("*************************************************");
        System.out.println("**                 Inscription                 **");
        System.out.println("*************************************************");
        nomFamille = entrerContexte("nom de famille");
        prenom = entrerContexte("prénom");
        adresse = entrerAdresse();
        dateNaissance = entrerDate();
        telephone = entrerTelephone();
        email = entrerEmail();
        password = entrerMotdepasse();
        cityNumber = enterCityNumber();

        InsertData.insertionUtilisateur(prenom,nomFamille,adresse,email,telephone,dateNaissance,password,cityNumber);
        System.out.println("Inscription fait avec succès, vous pouves vous connecter maintenant.");
        Display display = new Display();
        display.introScreen();
    }



    private static String entrerContexte(String text){
        Scanner sc = new Scanner(System.in);
        String contexte = "";
        System.out.print("** Entrez votre " + text + " :\t");
        contexte = sc.nextLine();
        if (contexte.isBlank()){
            System.err.println("Vous avez rentré des données invalides");
            entrerContexte(text);
        }
        return contexte;
    }

    private static String entrerAdresse(){
        Scanner sc = new Scanner(System.in);
        String adresse = "";
        System.out.print("** Entrez votre adresse :\t");
        adresse = sc.nextLine();
        if (adresse.isBlank() || !AddressVerificator.verifyAddress(adresse)){
            System.err.println("L'addresse que vous avez entrée n'existe pas");
            entrerAdresse();
        }
        return adresse;
    }

    private static LocalDate entrerDate(){
        LocalDate dateNaissance = null;
        Scanner sc = new Scanner(System.in);
        String date = "";
        System.out.print("** Entrez votre date (YYYY-MM-DD):\t");
        date = sc.nextLine();
        if (date.isBlank()){
            System.err.println("Le date n'existe pas.");
        }
        try {
            dateNaissance = LocalDate.parse(date);
            LocalDate today = LocalDate.now();
            int age = Period.between(dateNaissance, today).getYears();
            if (age < 18){
                System.err.println("Vous êtes trop jeune pour cette application");
                Display display = new Display();
                display.introScreen();
            }
        }catch (Exception e){
            System.err.println("Le date n'existe pas.");
            entrerDate();
        }
        return dateNaissance;
    }

    private static String entrerTelephone(){
        Scanner sc = new Scanner(System.in);
        String telephone = "";
        System.out.print("** Entrez votre telephone :\t");
        telephone = sc.nextLine();
        if (telephone.isBlank()){
            System.err.println("Le telephone n'existe pas.");
            entrerTelephone();
        }
        return telephone;
    }
    private static String entrerEmail(){
        Scanner sc = new Scanner(System.in);
        String email = "";
        System.out.print("** Entrez votre email :\t");
        email = sc.nextLine();
        if (email.isBlank()){
            System.err.println("Le email n'existe pas.");
            entrerEmail();
        }
        try{
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
        }catch (AddressException e){
            System.err.println("Le email n'existe pas.");
            entrerEmail();
        }
        return email;
    }
    private static String entrerMotdepasse(){
        Scanner sc = new Scanner(System.in);
        String motdepasse = "";
        String motdepasse2 = "";
        String passwordregex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

        Pattern pattern = Pattern.compile(passwordregex);

        System.out.print("** Entrez votre motdepasse :\t");
        motdepasse = sc.nextLine();
        if (motdepasse.isBlank()){
            System.err.println("Le format du mot de passe est incorrect.");
        }
        Matcher matcher = pattern.matcher(motdepasse);
        if (!matcher.matches()){
            System.err.println("Le format du mot de passe est incorrect.");
            entrerMotdepasse();
        }
        System.out.print("** Réentrez votre mot de passe pour confirmer :\t");
        motdepasse = sc.nextLine();
        if (motdepasse.isBlank() || !motdepasse.equals(motdepasse2)){
            System.err.println("Le mot de passe ne correspond pas à votre première ebtrée.");
            entrerMotdepasse();
        }
        return motdepasse;
    }
    private static int enterCityNumber(){
        Scanner sc = new Scanner(System.in);
        int cityNumber = -1;
        String city = "";
        System.out.print("** Entrez votre numéro de contrat à 8 chiffre si vous en avez un.\n Laissez vide si vous en avez pas ou si vous êtes un résident :\t");
        city = sc.nextLine();
        if (city.isBlank()){
            return cityNumber;
        }
        boolean test = city.matches("\\d{8}");
        if (!test){
            System.err.println("Votre numéro de ville ne respecte pas le format.");
            enterCityNumber();
        }
        cityNumber = Integer.parseInt(city);
        return cityNumber;
    }
}
