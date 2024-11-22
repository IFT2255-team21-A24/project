import ca.umontreal.dir.ift2255.team21.accounts.Account;
import ca.umontreal.dir.ift2255.team21.apihandler.TransformAddress;
import ca.umontreal.dir.ift2255.team21.databasehandler.ConnectionCheck;
import ca.umontreal.dir.ift2255.team21.databasehandler.PasswordHash;
import ca.umontreal.dir.ift2255.team21.distancecalculator.CalculateDistance;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestsArpad {

    @Test
    public void testAddress() {
        TransformAddress transformAddress = new TransformAddress();
        String adrress = "1002 rue Gerbault, Laval, QC";
        String coordonnees = "45.569061,-73.83979819999999";
        assertTrue(transformAddress.transform(adrress).equals(coordonnees));
    }

    @Test
    public void testData(){
        String email = "bob.johnson@test.org";
        String mdp = "J1dz3klzzj";
        Account account = ConnectionCheck.checkForUser(email, mdp);
        assertNotNull(account);
        email = "john.doe@email.ca";
        mdp = "lol";
        account = ConnectionCheck.checkForUser(email, mdp);
        assertNull(account);
    }

    @Test
    public void testQuartier() {
        String coordonnees = "45.569061,-73.83979819999999";
        assertTrue(CalculateDistance.trouverQuartier(coordonnees).equals("Fabreville"));

    }
}