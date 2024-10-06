package ca.umontreal.dir.ift2255.team21;

import ca.umontreal.dir.ift2255.team21.cli.Display;
import ca.umontreal.dir.ift2255.team21.databasehandler.InsertData;
import ca.umontreal.dir.ift2255.team21.databasehandler.PasswordHash;

import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
//        Display display = new Display();
//        display.introScreen();
        String pass = PasswordHash.hashPassword("Salut123");
        LocalDate date = LocalDate.of(2002,05,30);
        System.out.println(pass);
        InsertData insertData = new InsertData();
        insertData.insertion("Arpad", "Rigo", "pass",
                "arpadbotond.rigo@outlook.com","4384080719", date, pass, "arpi");


    }
}