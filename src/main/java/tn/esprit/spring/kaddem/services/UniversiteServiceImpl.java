package tn.esprit.spring.kaddem.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;

import java.util.List;
import java.util.Set;

@Service
public class UniversiteServiceImpl implements IUniversiteService {

    private static final Logger logger = Logger.getLogger(UniversiteServiceImpl.class);

    @Autowired
    UniversiteRepository universiteRepository;

    @Autowired
    DepartementRepository departementRepository;

    public UniversiteServiceImpl() {
    }

    public List<Universite> retrieveAllUniversites() {
        logger.info("Récupération de toutes les universités");
        List<Universite> universites = (List<Universite>) universiteRepository.findAll();
        logger.info("Nombre d'universités récupérées : " + universites.size());
        return universites;
    }

    public Universite addUniversite(Universite u) {
        logger.info("Ajout d'une nouvelle université : " + u);
        Universite universite = universiteRepository.save(u);
        logger.info("Université ajoutée avec succès : " + universite);
        return universite;
    }

    public Universite updateUniversite(Universite u) {
        logger.info("Mise à jour de l'université : " + u);
        Universite updatedUniversite = universiteRepository.save(u);
        logger.info("Université mise à jour avec succès : " + updatedUniversite);
        return updatedUniversite;
    }

    public Universite retrieveUniversite(Integer idUniversite) {
        logger.info("Récupération de l'université avec ID : " + idUniversite);
        Universite universite = universiteRepository.findById(idUniversite).orElse(null);
        if (universite != null) {
            logger.info("Université trouvée : " + universite);
        } else {
            logger.warn("Aucune université trouvée avec ID : " + idUniversite);
        }
        return universite;
    }

    public void deleteUniversite(Integer idUniversite) {
        logger.info("Suppression de l'université avec ID : " + idUniversite);
        universiteRepository.delete(retrieveUniversite(idUniversite));
        logger.info("Université supprimée avec succès");
    }

    public void assignUniversiteToDepartement(Integer idUniversite, Integer idDepartement) {
        logger.info("Affectation du département ID : " + idDepartement + " à l'université ID : " + idUniversite);
        Universite u = universiteRepository.findById(idUniversite).orElse(null);
        Departement d = departementRepository.findById(idDepartement).orElse(null);
        if (u != null && d != null) {
            u.getDepartements().add(d);
            universiteRepository.save(u);
            logger.info("Affectation réussie du département à l'université");
        } else {
            logger.warn("Université ou département introuvable pour l'affectation");
        }
    }

    public Set<Departement> retrieveDepartementsByUniversite(Integer idUniversite) {
        logger.info("Récupération des départements pour l'université ID : " + idUniversite);
        Universite u = universiteRepository.findById(idUniversite).orElse(null);
        Set<Departement> departements = (u != null) ? u.getDepartements() : null;
        if (departements != null) {
            logger.info("Nombre de départements trouvés : " + departements.size());
        } else {
            logger.warn("Aucune université trouvée avec ID : " + idUniversite);
        }
        return departements;
    }
}
