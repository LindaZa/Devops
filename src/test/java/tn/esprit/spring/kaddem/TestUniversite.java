package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.services.UniversiteServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UniversiteServiceImplTest {

    @InjectMocks
    UniversiteServiceImpl universiteService;

    @Mock
    UniversiteRepository universiteRepository;

    @Mock
    DepartementRepository departementRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllUniversites() {
        List<Universite> universites = new ArrayList<>();
        universites.add(new Universite(1, "Universite 1"));
        universites.add(new Universite(2, "Universite 2"));

        when(universiteRepository.findAll()).thenReturn(universites);

        List<Universite> result = universiteService.retrieveAllUniversites();
        assertEquals(2, result.size());
        verify(universiteRepository, times(1)).findAll();
    }

    @Test
    void testAddUniversite() {
        Universite universite = new Universite(1, "Universite Test");
        when(universiteRepository.save(universite)).thenReturn(universite);

        Universite result = universiteService.addUniversite(universite);
        assertNotNull(result);
        assertEquals("Universite Test", result.getNomUniv());
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    void testRetrieveUniversite() {
        Universite universite = new Universite(1, "Universite Test");
        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        Universite result = universiteService.retrieveUniversite(1);
        assertNotNull(result);
        assertEquals("Universite Test", result.getNomUniv());
        verify(universiteRepository, times(1)).findById(1);
    }

    @Test
    void testDeleteUniversite() {
        Universite universite = new Universite(1, "Universite Test");
        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));
        doNothing().when(universiteRepository).delete(universite);

        universiteService.deleteUniversite(1);
        verify(universiteRepository, times(1)).delete(universite);
    }
}
