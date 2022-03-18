package fr.raclette.exposition;

import fr.raclette.entities.Utilisateur;
import fr.raclette.repo.UtilisateurRepository;
import fr.raclette.services.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Service d'exposition REST des clients.
 * URL / exposée.
 */
@RestController
@RequestMapping("/")
public class UtilisateurController {
    Logger logger = LoggerFactory.getLogger(UtilisateurController.class);

    @Autowired
    private UtilisateurService service;

    /**
     * GET un client
     * @param utilisateur id du client
     * @return Client converti en JSON
     */
    @GetMapping("{id}")
    public Optional<Utilisateur> getClient(@PathVariable("id") Utilisateur utilisateur) {
        logger.info("Client : demande récup d'un client avec id:{}", utilisateur.getId());
        return service.findUser(utilisateur.getId());

    }

    /**
     * GET liste des clients
     * @return liste des clients en JSON. [] si aucun compte.
     */
    @GetMapping("")
    public Iterable<Utilisateur> getClients() {
        logger.info("Utilisateur : demande récup des utilisateurs");
        return service.findAllUsers();
    }

    /**
     * POST un client
     * @param utilisateur client à ajouter (import JSON)
     * @return client ajouté
     */
    @PostMapping("")
    public Utilisateur postClient(@RequestBody Utilisateur utilisateur) {
        logger.info("Client : demande CREATION d'un utilisateur avec id:{}", utilisateur.getId());
        return service.inscriptionUser(utilisateur);
    }

    /**
     * POST un client
     * @param utilisateur client à ajouter (import JSON)
     * @return client ajouté
     */
    @DeleteMapping
    public void deleteClient(@RequestBody Utilisateur utilisateur) {
        logger.info("Client : demande SUPPRESSION d'un utilisateur avec id:{}", utilisateur.getId());
        service.deleteUser(utilisateur);
    }
}
