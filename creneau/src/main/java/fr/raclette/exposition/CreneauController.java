package fr.raclette.exposition;

import fr.raclette.entities.Cours;
import fr.raclette.repo.CreneauRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * Service d'exposition REST des clients.
 * URL / exposée.
 */
@RestController
@RequestMapping("/")
public class CreneauController {
    Logger logger = LoggerFactory.getLogger(CreneauController.class);

    // Injection DAO clients
    @Autowired
    private CreneauRepository repository;

    /**
     * GET 1 cours
     * @param cours id du cours
     * @return Cours converti en JSON
     */
    @GetMapping("{id}")
    public Cours getCours(@PathVariable("id") Cours cours) {
        logger.info("Cours : demande récup d'un cours avec id:{}", cours.getId());
        return repository.findById(cours.getId()).get();
    }

    /**
     * GET liste des cours
     * @return liste des cours en JSON. [] si aucun compte.
     */
    @GetMapping("")
    public Iterable<Cours> getCours() {
        logger.info("Cours : demande récup des cours");
        return repository.findAll();
    }

    /**
     * POST un cours
     * @param cours à ajouter (import JSON)
     * @param niveauEnseignant niveau de l'enseignant
     * @return cours ajouté
     */
    @PostMapping("")
    public void postCours(@RequestBody Cours cours, @RequestBody String niveauEnseignant) {

        int dayInMiliseconds = 1000 * 60 * 60 * 24;

        logger.info("Cours : demande CREATION d'un cours avec id:{}", cours.getId());

        if (cours.getNiveau().equals(niveauEnseignant)
                && cours.getCreneau().getDate().getTime()
                >= System.currentTimeMillis()
                + (7 * dayInMiliseconds)) {

            repository.save(cours);
        }
    }
}
