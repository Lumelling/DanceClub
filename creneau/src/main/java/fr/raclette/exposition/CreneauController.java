package fr.raclette.exposition;

import fr.raclette.dto.Utilisateur;
import fr.raclette.entities.Cours;
import fr.raclette.exceptions.NiveauIncorrectException;
import fr.raclette.repo.CreneauRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Service d'exposition REST des clients.
 * URL / exposée.
 */
@RestController
@RequestMapping("/")
public class CreneauController {
    private final static String MSG_ERR_NIVEAU_INCORRECT = "Erreur, le niveau de l'utilisateur est incorrect";
    Logger logger = LoggerFactory.getLogger(CreneauController.class);

    // Injection DAO creneau
    @Autowired
    private CreneauRepository repository;

    @Autowired
    UtilisateurFeignController utilisateurFeignController;

    /**
     * GET 1 cours
     *
     * @param cours id du cours
     * @return Cours converti en JSON
     */
    @GetMapping("{id}")
    public ResponseEntity<?> getCours(@PathVariable("id") Cours cours) {
        logger.info("Cours : demande récup d'un cours avec id ");

        return ResponseEntity.status(HttpStatus.OK).body(cours);
    }

    /**
     * GET liste des cours
     *
     * @return liste des cours en JSON. [] si aucun compte.
     */
    @GetMapping("")
    public Iterable<Cours> getCours() {
        logger.info("Cours : demande récup des cours");
        return repository.findAll();
    }

    /**
     * GET les cours du niveau X
     *
     * @param niveau Niveau du cours
     * @return Cours converti en JSON
     */
    @GetMapping(value = "", params={"niveau"})
    public Iterable<Cours> getCoursAvecNiveau(
            @RequestParam(name = "niveau") int niveau) {
        logger.info("Cours : demande récup d'un cours avec niveau : {}");

        return repository.findCoursByNiveau(niveau);
    }

    /**
     * GET les cours dispensé par un enseignant
     *
     * @param idEnseignant id de l'enseignant
     * @return Cours converti en JSON
     */
    @GetMapping(value = "", params="idEnseignant")
    public Iterable<Cours> getCoursAvecIdEnseignant(@RequestParam(name = "idEnseignant") Long idEnseignant) {
        logger.info("Cours : demande récup d'un cours avec niveau ");

        return repository.findCoursByIdEnseignant(idEnseignant);

    }

    /**
     * DELETE un cours
     */
    @DeleteMapping("{id}")
    public void deleteCours(@PathVariable("id") Long id) {
        logger.info("Cours : demande de suppression d'un cours avec id ");

        repository.deleteById(id);
    }

    /**
     * POST un cours
     *
     * @param cours à ajouter (import JSON)
     * @return cours ajouté
     */
    @PostMapping("")
    public Cours postCours(@RequestBody Cours cours) {

        logger.info("Cours : demande CREATION d'un cours avec id:{}", cours.getId());
        return repository.save(cours);
    }
}