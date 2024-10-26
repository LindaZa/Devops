package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tn.esprit.spring.kaddem.entities.Departement;

import static org.junit.jupiter.api.Assertions.*;

public class DepartementTest {

    private Departement departement;

    @BeforeEach
    public void setUp() {
        // Initialisation d'un nouvel objet Departement avant chaque test
        departement = new Departement();
    }

    @Test
    public void testSetAndGetNomDepart() {
        String nom = "Informatique";
        departement.setNomDepart(nom);
        assertEquals(nom, departement.getNomDepart(), "Le nom du département devrait être celui défini");
    }

    @Test
    public void testSetAndGetIdDepart() {
        Integer id = 1;
        departement.setIdDepart(id);
        assertEquals(id, departement.getIdDepart(), "L'ID du département devrait être celui défini");
    }

    @Test
    public void testConstructeurAvecNomDepart() {
        Departement departement = new Departement("Sciences");
        assertEquals("Sciences", departement.getNomDepart(), "Le nom du département devrait être 'Sciences'");
    }

    @Test
    public void testConstructeurAvecIdEtNomDepart() {
        Departement departement = new Departement(1, "Arts");
        assertEquals(1, departement.getIdDepart(), "L'ID du département devrait être 1");
        assertEquals("Arts", departement.getNomDepart(), "Le nom du département devrait être 'Arts'");
    }
}
