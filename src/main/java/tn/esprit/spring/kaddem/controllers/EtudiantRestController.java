package tn.esprit.spring.kaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.entities.Etudiant;
import tn.esprit.spring.kaddem.services.IEtudiantService;
import org.apache.logging.log4j.LogManager; // Import pour Log4j 2.x
import org.apache.logging.log4j.Logger; // Import pour Log4j 2.x

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/etudiant")
public class EtudiantRestController {

	private static final Logger logger = LogManager.getLogger(EtudiantRestController.class); // Création du logger

	@Autowired
	IEtudiantService etudiantService;

	// http://localhost:8089/Kaddem/etudiant/retrieve-all-etudiants
	@GetMapping("/retrieve-all-etudiants")
	public List<Etudiant> getEtudiants() {
		logger.info("Récupération de tous les étudiants."); // Log d'information
		List<Etudiant> listEtudiants = etudiantService.retrieveAllEtudiants();
		logger.debug("Liste des étudiants récupérée : " + listEtudiants); // Log de debug
		return listEtudiants;
	}

	// http://localhost:8089/Kaddem/etudiant/retrieve-etudiant/8
	@GetMapping("/retrieve-etudiant/{etudiant-id}")
	public Etudiant retrieveEtudiant(@PathVariable("etudiant-id") Integer etudiantId) {
		logger.info("Récupération de l'étudiant avec l'ID : " + etudiantId);
		Etudiant etudiant = etudiantService.retrieveEtudiant(etudiantId);
		if (etudiant == null) {
			logger.warn("Aucun étudiant trouvé avec l'ID : " + etudiantId); // Log d'avertissement
		}
		return etudiant;
	}

	// http://localhost:8089/Kaddem/etudiant/add-etudiant
	@PostMapping("/add-etudiant")
	public Etudiant addEtudiant(@RequestBody Etudiant e) {
		logger.info("Ajout d'un nouvel étudiant : " + e);
		Etudiant etudiant = etudiantService.addEtudiant(e);
		logger.debug("Étudiant ajouté : " + etudiant); // Log de debug
		return etudiant;
	}

	// http://localhost:8089/Kaddem/etudiant/remove-etudiant/1
	@DeleteMapping("/remove-etudiant/{etudiant-id}")
	public void removeEtudiant(@PathVariable("etudiant-id") Integer etudiantId) {
		logger.info("Suppression de l'étudiant avec l'ID : " + etudiantId);
		etudiantService.removeEtudiant(etudiantId);
		logger.debug("Étudiant avec l'ID " + etudiantId + " a été supprimé."); // Log de debug
	}

	// http://localhost:8089/Kaddem/etudiant/update-etudiant
	@PutMapping("/update-etudiant")
	public Etudiant updateEtudiant(@RequestBody Etudiant e) {
		logger.info("Mise à jour de l'étudiant : " + e);
		Etudiant etudiant = etudiantService.updateEtudiant(e);
		logger.debug("Étudiant mis à jour : " + etudiant); // Log de debug
		return etudiant;
	}

	// Affecter étudiant à département
	@PutMapping(value="/affecter-etudiant-departement/{etudiantId}/{departementId}")
	public void affecterEtudiantToDepartement(@PathVariable("etudiantId") Integer etudiantId, @PathVariable("departementId") Integer departementId) {
		logger.info("Affectation de l'étudiant avec l'ID : " + etudiantId + " au département avec l'ID : " + departementId);
		etudiantService.assignEtudiantToDepartement(etudiantId, departementId);
	}

	// Ajouter et affecter étudiant à équipe et contrat
	@PostMapping("/add-assign-Etudiant/{idContrat}/{idEquipe}")
	@ResponseBody
	public Etudiant addEtudiantWithEquipeAndContract(@RequestBody Etudiant e, @PathVariable("idContrat") Integer idContrat, @PathVariable("idEquipe") Integer idEquipe) {
		logger.info("Ajout de l'étudiant : " + e + ", affectation à l'équipe ID : " + idEquipe + " et contrat ID : " + idContrat);
		Etudiant etudiant = etudiantService.addAndAssignEtudiantToEquipeAndContract(e, idContrat, idEquipe);
		logger.debug("Étudiant ajouté et affecté : " + etudiant); // Log de debug
		return etudiant;
	}

	@GetMapping(value = "/getEtudiantsByDepartement/{idDepartement}")
	public List<Etudiant> getEtudiantsParDepartement(@PathVariable("idDepartement") Integer idDepartement) {
		logger.info("Récupération des étudiants par département ID : " + idDepartement);
		List<Etudiant> etudiants = etudiantService.getEtudiantsByDepartement(idDepartement);
		logger.debug("Liste des étudiants récupérée pour le département : " + etudiants); // Log de debug
		return etudiants;
	}
}
