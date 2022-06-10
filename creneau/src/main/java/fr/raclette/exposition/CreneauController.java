package fr.raclette.exposition;

import fr.raclette.dto.Utilisateur;
import fr.raclette.entities.Cours;
import fr.raclette.repo.CreneauRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Service d'exposition REST des clients.
 * URL / exposée.
 */
@RestController
@RequestMapping("/")
public class CreneauController {

    private final static String MSG_ERR_ROLE_INCORRECT = "Erreur, l'utilisateur n'est pas autorisé";
    private final static String MSG_ERR_ENSEIGNANT_INCORRECT = "Erreur, l'enseignant n'existe pas";
    private final static String MSG_ERR_NIVEAU_INCORRECT = "Erreur, le niveau du cours est incorrect";
    private final static String MSG_ERR_DATE_INCORRECTE = "Erreur, la date du cours est incorrecte";
    private final static String MSG_ERR_NIVEAU_ENSEIGNANT_INCORRECT = "Erreur, le niveau de l'enseignant est incorrect";
    private final static String MSG_COURS_AJOUTE = "Le cours a bien été ajouté";

    private final static Long JOUR_EN_MILLISECONDES = 1440000L;

    Logger logger = LoggerFactory.getLogger(CreneauController.class);

    // Injection DAO creneau
    @Autowired
    private CreneauRepository repository;

    @Autowired
    UtilisateurFeignController utilisateurFeignController;

    private final int NIVEAU_MINIMUM = 1;
    private final int NIVEAU_MAXIMUM = 5;

    /**
     * GET 1 cours
     *
     * @param cours id du cours
     * @return Cours converti en JSON
     */
    @GetMapping("{id}")
    public Cours getCours(@PathVariable("id") Cours cours) {
        logger.info("Cours : demande récup d'un cours avec id ");

        return cours;
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
    public ResponseEntity<String> postCours(@RequestBody Cours cours) {

        logger.info("Cours : demande CREATION d'un cours avec id:{}", cours.getId());

        Utilisateur prof;

        try {
            prof =  utilisateurFeignController.get(cours.getIdEnseignant());

            if (prof.getExpertise() < cours.getNiveau()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("[CODE 401] : " + MSG_ERR_NIVEAU_ENSEIGNANT_INCORRECT);
            }

            Date dateCours = new SimpleDateFormat("dd/MM/yyyy").parse(cours.getCreneau().getDate());
            Date dateDuJour = new Date();

            if (dateCours.getTime() < (dateDuJour.getTime() + (JOUR_EN_MILLISECONDES * 7))) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("[CODE 401] : " + MSG_ERR_DATE_INCORRECTE);
            }

        } catch (ParseException parseException) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("[CODE 401] : " + MSG_ERR_DATE_INCORRECTE);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("[CODE 401] : " + MSG_ERR_ENSEIGNANT_INCORRECT);
        }

        if (cours.getNiveau() < NIVEAU_MINIMUM || cours.getNiveau() > NIVEAU_MAXIMUM) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("[CODE 401] : " + MSG_ERR_NIVEAU_INCORRECT);
        }

        if (!prof.getRole().equals("enseignant")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("[CODE 401] : " + MSG_ERR_ROLE_INCORRECT);
        }

        repository.save(cours);
        return ResponseEntity.status(HttpStatus.OK).body("[CODE 200] : " + MSG_COURS_AJOUTE);
    }
}