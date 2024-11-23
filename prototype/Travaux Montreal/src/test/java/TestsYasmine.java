import ca.umontreal.dir.ift2255.team21.accounts.Account;
import ca.umontreal.dir.ift2255.team21.databasehandler.ConnectionCheck;
import ca.umontreal.dir.ift2255.team21.databasehandler.PasswordHash;
import ca.umontreal.dir.ift2255.team21.distancecalculator.CalculateDistance;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class TestsYasmine {

    @Test
    public void testCheckPassword() {
    String password = "testPassword123!";
    String hashed = PasswordHash.hashPassword(password);
    assertTrue("La vérification du mot de passe devrait réussir", PasswordHash.checkPassword(password, hashed));

    String wrongPassword = "wrongPassword!";
    assertFalse("La vérification du mot de passe devrait échouer pour un mauvais mot de passe", PasswordHash.checkPassword(wrongPassword, hashed));
}

    @Test
    public void testSuccessfulUserRetrieval() throws Exception {
        String correctEmail = "user@example.com";
        String correctPassword = "correct_password";

        Account result = ConnectionCheck.checkForUser(correctEmail, correctPassword);

        assertNull("L'utilisateur n'aurait pas dû être récupéré vue que pas dans la base de donnée.", result);
    }



    @Test
    public void testTrouverQuartier() throws IOException {
        String quartier = CalculateDistance.trouverQuartier("45.5017,-73.5673");
        System.out.println(quartier);
        assertEquals("Ville-Marie", quartier);
    }
}
