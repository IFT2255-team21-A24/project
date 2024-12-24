package com.maville;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class ApplicationMaVilleTest {

    @Test
    void testInscriptionResident() {
        Database databaseMock = mock(Database.class);
        Resident resident = new Resident("John Doe", "john.doe@example.com", "password123");

        when(databaseMock.ajouterResident(resident)).thenReturn(true);

        ApplicationMaVille app = new ApplicationMaVille(databaseMock, mock(NotificationService.class));
        boolean result = app.inscrireResident(resident);

        assertTrue(result, "L'inscription du résident devrait être réussie");
        verify(databaseMock).ajouterResident(resident);
    }

    @Test
    void testSoumissionRequete() {
        Database databaseMock = mock(Database.class);
        Requete requete = new Requete("Réparation", "Travaux sur la route");
        Resident resident = new Resident("Alice", "alice@example.com", "password");

        when(databaseMock.enregistrerRequete(resident, requete)).thenReturn(true);

        ApplicationMaVille app = new ApplicationMaVille(databaseMock, mock(NotificationService.class));
        boolean result = app.soumettreRequete(resident, requete);

        assertTrue(result, "La soumission de la requête devrait être réussie");
        verify(databaseMock).enregistrerRequete(resident, requete);
    }

    @Test
    void testRecuperationDonnees() {
        Database databaseMock = mock(Database.class);
        List<Requete> mockRequetes = List.of(
            new Requete("Réparation", "Travaux"),
            new Requete("Maintenance", "Ampoules")
        );

        when(databaseMock.recupererRequetes()).thenReturn(mockRequetes);

        ApplicationMaVille app = new ApplicationMaVille(databaseMock, mock(NotificationService.class));
        List<Requete> requetes = app.recupererRequetes();

        assertEquals(2, requetes.size(), "Il devrait y avoir 2 requêtes récupérées");
        assertEquals("Réparation", requetes.get(0).getTitre());
        assertEquals("Maintenance", requetes.get(1).getTitre());
    }
}
