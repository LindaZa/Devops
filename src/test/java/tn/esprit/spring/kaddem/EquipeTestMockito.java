package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import tn.esprit.spring.kaddem.entities.Equipe;
import tn.esprit.spring.kaddem.entities.Niveau;
import tn.esprit.spring.kaddem.repositories.EquipeRepository;
import tn.esprit.spring.kaddem.services.EquipeServiceImpl;

import java.util.Optional;

public class EquipeTestMockito {

    private Equipe equipe;
    private EquipeRepository equipeRepository;
    private EquipeServiceImpl equipeService;

    @BeforeEach
    public void setUp() {
        equipe = new Equipe("Equipe 1", Niveau.JUNIOR);  // Creating a new equipe
        equipe.setIdEquipe(1);  // Setting a mock ID
        equipeRepository = mock(EquipeRepository.class);  // Mocking the repository
        equipeService = new EquipeServiceImpl(equipeRepository);  // Injecting the mock into the service
    }

    // --- CREATE Test ---
    @Test
    public void testAddEquipe() {
        when(equipeRepository.save(equipe)).thenReturn(equipe);  // Mocking save

        Equipe addedEquipe = equipeService.addEquipe(equipe);  // Calling the service method

        assertNotNull(addedEquipe);  // Ensure the equipe is not null
        assertEquals("Equipe 1", addedEquipe.getNomEquipe());  // Check if the name is correct
        assertEquals(Niveau.JUNIOR, addedEquipe.getNiveau());  // Check if the level is correct

        verify(equipeRepository, times(1)).save(equipe);  // Verify save was called once
    }

    // --- READ Test ---
    @Test
    public void testRetrieveEquipe() {
        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));  // Mocking findById

        Equipe retrievedEquipe = equipeService.retrieveEquipe(1);  // Calling the service method

        assertNotNull(retrievedEquipe);  // Ensure the equipe is not null
        assertEquals(1, retrievedEquipe.getIdEquipe());  // Check if the ID matches
        assertEquals("Equipe 1", retrievedEquipe.getNomEquipe());  // Check if the name matches

        verify(equipeRepository, times(1)).findById(1);  // Verify findById was called once
    }

    // --- UPDATE Test ---
    @Test
    public void testUpdateEquipe() {
        equipe.setNomEquipe("Updated Equipe");  // Changing the name of the equipe
        when(equipeRepository.save(equipe)).thenReturn(equipe);  // Mocking save

        Equipe updatedEquipe = equipeService.updateEquipe(equipe);  // Calling the service method

        assertNotNull(updatedEquipe);  // Ensure the equipe is not null
        assertEquals("Updated Equipe", updatedEquipe.getNomEquipe());  // Check if the name was updated

        verify(equipeRepository, times(1)).save(equipe);  // Verify save was called once
    }

    // --- DELETE Test ---
    @Test
    public void testDeleteEquipe() {
        when(equipeRepository.findById(1)).thenReturn(Optional.of(equipe));  // Mocking findById
        doNothing().when(equipeRepository).delete(equipe);  // Mocking delete

        equipeService.deleteEquipe(1);  // Calling the service method

        verify(equipeRepository, times(1)).delete(equipe);  // Verify delete was called once
    }
}
