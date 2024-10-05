package ca.umontreal.dir.ift2255.team21;

import ca.umontreal.dir.ift2255.team21.cli.Display;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        Display display = new Display();
        int choiceIntro = display.introScreen();
        System.out.println(choiceIntro);
        if (choiceIntro == 1){
        }

    }
}