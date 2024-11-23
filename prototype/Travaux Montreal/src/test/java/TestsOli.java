import ca.umontreal.dir.ift2255.team21.accounts.Account;
import ca.umontreal.dir.ift2255.team21.databasehandler.TravauxDB;
import ca.umontreal.dir.ift2255.team21.databasehandler.PasswordHash;
import ca.umontreal.dir.ift2255.team21.distancecalculator.CalculateDistance;
import ca.umontreal.dir.ift2255.team21.entraves.Entraves;
import ca.umontreal.dir.ift2255.team21.entraves.Travaux;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class TestsOli {

    @Test
    public void testPasswordHashingAndVerification() {
        String originalPassword = "securePassword123!";
        String hashedPassword = PasswordHash.hashPassword(originalPassword);
        assertTrue("Le mot de passe devrait être vérifié correctement",
                PasswordHash.checkPassword(originalPassword, hashedPassword));

        String wrongPassword = "wrongPassword!";
        assertFalse("Le mot de passe ne devrait pas correspondre",
                PasswordHash.checkPassword(wrongPassword, hashedPassword));
    }

    @Test
    public void testListerEntravesProches() {
        // Tester la méthode qui liste les entraves proches d'un quartier donné
        ArrayList<Entraves> entraves = new ArrayList<>();
        Entraves entrave1 = new Entraves("1", "Pl. Martial",  "Fabreville", "aa1951651ad15",45.575882422096825, -73.82017115600885);
        Entraves entrave2 = new Entraves("2", "Blv Curée-Labelle", "Chomedey", "av898ad99a96", 45.538025304710544, -73.73686142312955);
        entraves.add(entrave1);
        entraves.add(entrave2);

        String coordinates = "45.569061,-73.83979819999999"; // Coordonnées pour Fabreville
        ArrayList<Entraves> result = CalculateDistance.listerEntravesProches(entraves, coordinates);

        assertEquals("Une seule entrave devrait être proche du quartier Fabreville", 1, result.size());
        assertEquals("L'entrave proche devrait avoir l'ID 0 (on indexe à 0)", 0, result.indexOf(result.get(0)));
    }

    @Test
    public void testRetrieveTravaux() {
        // Tester la récupération des travaux depuis la base de données
        ArrayList<Travaux> travaux = TravauxDB.retrieveTravaux();
        assertNotNull("La liste des travaux ne devrait pas être nulle", travaux);
        assertTrue("La liste des travaux devrait contenir des éléments", travaux.size() > 0);
    }
}