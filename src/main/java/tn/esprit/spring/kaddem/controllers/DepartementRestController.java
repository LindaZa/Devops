package tn.esprit.spring.kaddem.controllers;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.services.IDepartementService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/departement")
public class DepartementRestController {

	private static final Logger logger = LogManager.getLogger(DepartementRestController.class);
	private final IDepartementService departementService;

	// http://localhost:8089/Kaddem/departement/retrieve-all-departements
	@GetMapping("/retrieve-all-departements")
	public List<Departement> getDepartements() {
		logger.info("Fetching all departments");
		List<Departement> listDepartements;
		try {
			listDepartements = departementService.retrieveAllDepartements();
			logger.debug("Fetched departments count: {}", listDepartements.size());
		} catch (Exception e) {
			logger.error("Error while fetching all departments: {}", e.getMessage());
			throw e;
		}
		return listDepartements;
	}

	// http://localhost:8089/Kaddem/departement/retrieve-departement/8
	@GetMapping("/retrieve-departement/{departement-id}")
	public Departement retrieveDepartement(@PathVariable("departement-id") Integer departementId) {
		logger.info("Fetching department with ID: {}", departementId);
		Departement departement;
		try {
			departement = departementService.retrieveDepartement(departementId);
			if (departement != null) {
				logger.debug("Department retrieved: {}", departement);
			} else {
				logger.warn("Department with ID {} not found", departementId);
			}
		} catch (Exception e) {
			logger.error("Error retrieving department with ID {}: {}", departementId, e.getMessage());
			throw e;
		}
		return departement;
	}

	// http://localhost:8089/Kaddem/departement/add-departement
	@PostMapping("/add-departement")
	public Departement addDepartement(@RequestBody Departement d) {
		logger.info("Adding new department: {}", d);
		Departement departement;
		try {
			departement = departementService.addDepartement(d);
			logger.debug("Department added successfully: {}", departement);
		} catch (Exception e) {
			logger.error("Error while adding department: {}", e.getMessage());
			throw e;
		}
		return departement;
	}

	// http://localhost:8089/Kaddem/departement/remove-departement/1
	@DeleteMapping("/remove-departement/{departement-id}")
	public void removeDepartement(@PathVariable("departement-id") Integer departementId) {
		logger.info("Removing department with ID: {}", departementId);
		try {
			departementService.deleteDepartement(departementId);
			logger.debug("Department with ID {} removed successfully", departementId);
		} catch (Exception e) {
			logger.error("Error while removing department with ID {}: {}", departementId, e.getMessage());
			throw e;
		}
	}

	// http://localhost:8089/Kaddem/departement/update-departement
	@PutMapping("/update-departement")
	public Departement updateDepartement(@RequestBody Departement e) {
		logger.info("Updating department: {}", e);
		Departement departement;
		try {
			departement = departementService.updateDepartement(e);
			logger.debug("Department updated successfully: {}", departement);
		} catch (Exception ex) {
			logger.error("Error while updating department: {}", ex.getMessage());
			throw ex;
		}
		return departement;
	}
}
