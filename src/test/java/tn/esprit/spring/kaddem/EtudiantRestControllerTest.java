package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.controllers.EtudiantRestController;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Option;
import tn.esprit.spring.kaddem.services.EtudiantServiceImpl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class EtudiantRestControllerTest {

    private Etudiant etudiant;

    @Mock
    private EtudiantServiceImpl etudiantServiceMock; // Simulation du service EtudiantService

    @InjectMocks
    private EtudiantRestController etudiantRestController; // Le contrôleur à tester

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialise les mocks
        // Initialisation d'un nouvel objet Etudiant avant chaque test
        etudiant = new Etudiant("Zaafrani", "Linda", Option.GAMIX);
    }

    @Test
    public void testSetAndGetNomEtudiant() {
        String nom = "Zaafrani";
        etudiant.setNomE(nom);
        assertEquals(nom, etudiant.getNomE(), "Le nom de l'étudiant devrait être celui défini");
    }

    @Test
    public void testSetAndGetPrenomEtudiant() {
        String prenom = "Linda";
        etudiant.setPrenomE(prenom);
        assertEquals(prenom, etudiant.getPrenomE(), "Le prénom de l'étudiant devrait être celui défini");
    }

    @Test
    public void testSetAndGetIdEtudiant() {
        Integer id = 1;
        etudiant.setIdEtudiant(id);
        assertEquals(id, etudiant.getIdEtudiant(), "L'ID de l'étudiant devrait être celui défini");
    }

    @Test
    public void testConstructeurAvecNomEtPrenom() {
        Etudiant etudiant = new Etudiant("Zaafrani", "Linda");
        assertEquals("Zaafrani", etudiant.getNomE(), "Le nom de l'étudiant devrait être 'Zaafrani'");
        assertEquals("Linda", etudiant.getPrenomE(), "Le prénom de l'étudiant devrait être 'Linda'");
    }

    @Test
    public void testConstructeurAvecNomEtPrenomEtOption() {
        // Création d'un étudiant avec le constructeur prenant nom, prénom et option
        Etudiant etudiant = new Etudiant("Zaafrani", "Linda", Option.GAMIX);

        // Vérification que les valeurs ont été correctement affectées
        assertEquals("Zaafrani", etudiant.getNomE(), "Le nom de l'étudiant devrait être 'Zaafrani'");
        assertEquals("Linda", etudiant.getPrenomE(), "Le prénom de l'étudiant devrait être 'Linda'");
        assertEquals(Option.GAMIX, etudiant.getOp(), "L'option de l'étudiant devrait être 'GAMIX'");
    }

    @Test
    public void testGetEtudiantById() {
        // Simuler l'appel à la méthode getEtudiantById(1) du service mocké
        when(etudiantServiceMock.retrieveEtudiant(1)).thenReturn(etudiant);

        // Appeler la méthode du contrôleur (supposée interagir avec le service)
        Etudiant result = etudiantRestController.retrieveEtudiant(1);

        // Vérification de l'interaction avec le mock
        verify(etudiantServiceMock).retrieveEtudiant(1);
        assertNotNull(result, "L'étudiant retourné ne devrait pas être nul");
        assertEquals("Zaafrani", result.getNomE(), "Le nom de l'étudiant devrait être 'Zaafrani'");
        assertEquals("Linda", result.getPrenomE(), "Le prénom de l'étudiant devrait être 'Linda'");
    }
}
