package fr.raclette.exposition;

import fr.raclette.entities.Cours;
import fr.raclette.exceptions.NiveauIncorrectException;
import fr.raclette.repo.CreneauRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Service d'exposition REST des clients.
 * URL / exposée.
 */
@RestController
@RequestMapping("/")
public class CreneauController {
    Logger logger = LoggerFactory.getLogger(CreneauController.class);

    private final static String MSG_ERR_NIVEAU_INCORRECT = "Erreur, le niveau de l'utilisateur est incorrect";

    // Injection DAO creneau
    @Autowired
    private CreneauRepository repository;

    /**
     * GET 1 cours
     * @param  id du cours
     * @return Cours converti en JSON
     */
    @GetMapping("{id}")
    public Cours getCours(@PathVariable("id") Long id) {
        logger.info("Cours : demande récup d'un cours avec id ");

        return repository.findById(id).get();
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
     * @return cours ajouté
     */
    @PostMapping("")
    public void postCours(@RequestBody Cours cours) {

        logger.info("Cours : demande CREATION d'un cours avec id:{}", cours.getId());


        repository.save(cours);
    }
}
