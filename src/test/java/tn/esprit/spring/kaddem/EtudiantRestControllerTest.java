

package tn.esprit.spring.kaddem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tn.esprit.spring.kaddem.controllers.EtudiantRestController;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.entities.Option;
import tn.esprit.spring.kaddem.services.IEtudiantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
public class EtudiantRestControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private IEtudiantService etudiantService;
    @InjectMocks
    private EtudiantRestController etudiantRestController;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetEtudiants() throws Exception {
        // Mock data
        Etudiant etudiant1 = new Etudiant(1, "Linda", "Zaafrani", Option.GAMIX);
        Etudiant etudiant2 = new Etudiant(2, "Yasmine", "Benz", Option.NIDS);
        List<Etudiant> etudiants = Arrays.asList(etudiant1, etudiant2);
        // Mock service
        when(etudiantService.retrieveAllEtudiants()).thenReturn(etudiants);
        // Perform GET request and verify the response
        mockMvc.perform(get("/etudiant/retrieve-all-etudiants"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].prenom").value("Linda"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].prenom").value("Yasmine"));
        verify(etudiantService, times(1)).retrieveAllEtudiants();
    }
    @Test
    public void testRetrieveEtudiant() throws Exception {
        // Mock data
        Etudiant etudiant = new Etudiant(1, "Linda", "Zaafrani", Option.GAMIX);
        // Mock service
        when(etudiantService.retrieveEtudiant(1)).thenReturn(etudiant);
        // Perform GET request and verify the response
        mockMvc.perform(get("/etudiant/retrieve-etudiant/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.prenom").value("Linda"));
        verify(etudiantService, times(1)).retrieveEtudiant(1);
    }
    @Test
    public void testAddEtudiant() throws Exception {
        // Mock data
        Etudiant etudiant = new Etudiant(1, "Linda", "Zaafrani", Option.GAMIX);
        // Mock service
        when(etudiantService.addEtudiant(any(Etudiant.class))).thenReturn(etudiant);
        // Perform POST request and verify the response
        mockMvc.perform(post("/etudiant/add-etudiant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(etudiant)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.prenom").value("Linda"));
        verify(etudiantService, times(1)).addEtudiant(any(Etudiant.class));
    }
    @Test
    public void testRemoveEtudiant() throws Exception {
        // Perform DELETE request and verify the response
        mockMvc.perform(delete("/etudiant/remove-etudiant/1"))
                .andExpect(status().isOk());
        verify(etudiantService, times(1)).removeEtudiant(1);
    }
    @Test
    public void testUpdateEtudiant() throws Exception {
        // Mock data
        Etudiant etudiant = new Etudiant(1, "Linda", "Zaafrani", Option.NIDS);
        // Mock service
        when(etudiantService.updateEtudiant(any(Etudiant.class))).thenReturn(etudiant);
        // Perform PUT request and verify the response
        mockMvc.perform(put("/etudiant/update-etudiant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(etudiant)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.prenom").value("Linda"));
        verify(etudiantService, times(1)).updateEtudiant(any(Etudiant.class));
    }
    @Test
    public void testAffecterEtudiantToDepartement() throws Exception {
        // Test data
        Integer etudiantId = 1;
        Integer departementId = 2;
        // Perform PUT request and verify the response
        mockMvc.perform(put("/etudiant/affecter-etudiant-departement/{etudiantId}/{departementId}", etudiantId, departementId))
                .andExpect(status().isOk());
        // Verify service method was called
        verify(etudiantService, times(1)).assignEtudiantToDepartement(etudiantId, departementId);
    }
    @Test
    public void testAddEtudiantWithEquipeAndContract() throws Exception {
        // Test data
        Etudiant etudiant = new Etudiant(1, "Linda", "Zaafrani", Option.GAMIX);
        Integer idContrat = 3;
        Integer idEquipe = 4;
        // Mock service
        when(etudiantService.addAndAssignEtudiantToEquipeAndContract(any(Etudiant.class), eq(idContrat), eq(idEquipe)))
                .thenReturn(etudiant);
        // Perform POST request and verify the response
        mockMvc.perform(post("/etudiant/add-assign-Etudiant/{idContrat}/{idEquipe}", idContrat, idEquipe)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(etudiant)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.prenom").value("Linda"));
        // Verify service method was called
        verify(etudiantService, times(1)).addAndAssignEtudiantToEquipeAndContract(any(Etudiant.class), eq(idContrat), eq(idEquipe));
    }
    @Test
    public void testGetEtudiantsParDepartement() throws Exception {
        // Test data
        Integer idDepartement = 2;
        Etudiant etudiant1 = new Etudiant(1, "Linda", "Zaafrani", Option.GAMIX);
        Etudiant etudiant2 = new Etudiant(2, "Yasmine", "Benz", Option.NIDS);
        List<Etudiant> etudiants = Arrays.asList(etudiant1, etudiant2);
        // Mock service
        when(etudiantService.getEtudiantsByDepartement(idDepartement)).thenReturn(etudiants);
        // Perform GET request and verify the response
        mockMvc.perform(get("/etudiant/getEtudiantsByDepartement/{idDepartement}", idDepartement))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].prenom").value("Linda"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].prenom").value("Yasmine"));
        // Verify service method was called
        verify(etudiantService, times(1)).getEtudiantsByDepartement(idDepartement);
    }
}