package fr.raclette.exposition;

import fr.raclette.entities.Utilisateur;
import fr.raclette.services.UtilisateurService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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
     * @param utilisateur : id du client à récupérer
     * @return Client converti en JSON
     */
    @GetMapping("{id}")
    public Optional<Utilisateur> getUser(@PathVariable("id") Utilisateur utilisateur) {
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
     * @param utilisateur : client à ajouter (import JSON)
     * @return client ajouté
     */
    @PostMapping("")
    public ResponseEntity<String> postUser(@RequestBody Utilisateur utilisateur) {
        List<Utilisateur> usersCollection = new ArrayList<>();
        service.findAllUsers().forEach(usersCollection::add);
        logger.info("Client : demande CREATION d'un utilisateur avec rôle : {}", utilisateur.getRole());
        logger.info("Contains : {}",usersCollection.contains(utilisateur.getRole().equals("president")));
        //si le niveau d'expertise est pas entre 0 et 5
        if (utilisateur.getExpertise() > 5 || utilisateur.getExpertise() < 0) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("[CODE 401] : Le niveau d'expertise doit être compris entre 0 et 5\n");
        //si le rôle est pas IN(secrétaire, membre, enseignant, président)
        } else if (!utilisateur.getRole().equals("secretaire") && !utilisateur.getRole().equals("membre") && !utilisateur.getRole().equals("enseignant") && !utilisateur.getRole().equals("president")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("[CODE 401] : Le rôle doit être compris dans : (secretaire, membre, enseignant, president)\n");
        //si il y a déjà un président et que on essaye d'en ajouter un nouveau
        } else if (utilisateur.getRole().equals("president") && service.RoleExist("president")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("[CODE 401] : Il ne peut y avoir qu'un.e seul.e président.e\n");
        }else{
            //création de l'utilisateur
            logger.info("Client : demande CREATION d'un utilisateur avec id : {}", utilisateur.getId());
            service.inscriptionUser(utilisateur);
            return ResponseEntity.ok().body("L'utilisateur d'id {"+utilisateur.getId()+"} à bien été créé");
        }
    }

    /**
     * PUT (Modifie) un utilisateur pour le rendre Expert
     * @param utilisateur : client à ajouter
     * @return client ajouté
     */
    @PutMapping("{idModificateur}")
    public ResponseEntity<String> putExpertiseUser(@RequestBody Utilisateur utilisateur, @PathVariable("idModificateur") long idModificateur) throws Exception {
        //si pas entre 1 et 5 return 401
        if (utilisateur.getExpertise() > 5 || utilisateur.getExpertise() < 0) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("[CODE 401] : Le niveau d'expertise doit être compris entre 0 et 5\n");
            //si le rôle est pas IN(secrétaire, membre, enseignant, président)
        } else if (!utilisateur.getRole().equals("secretaire") && !utilisateur.getRole().equals("membre") && !utilisateur.getRole().equals("enseignant") && !utilisateur.getRole().equals("president")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("[CODE 401] : Le rôle doit être compris dans : (secretaire, membre, enseignant, president)\n");
            //si il y a déjà un président et que on essaye d'en ajouter un nouveau
        } else if (utilisateur.getRole().equals("president") && service.RoleExist("president")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("[CODE 401] : Il ne peut y avoir qu'un.e seul.e président.e\n");
        }else{
            //création de l'utilisateur
            logger.info("Client : demande CREATION d'un utilisateur avec id : {}", utilisateur.getId());
            try{
                service.updateUser(utilisateur,idModificateur);
                return ResponseEntity.ok().body("L'utilisateur d'id {"+utilisateur.getId()+"} à bien été modifié");
            }catch (Exception e){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("[CODE 401] : Vous devez être secrétaire pour modifier un utilisateur \n");
            }
        }
    }

    /**
     * DELETE un client
     * @param id : client à supprimer
     * @return
     */
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
        logger.info("Client : demande SUPPRESSION d'un utilisateur avec id:{}", id);
        service.deleteUser(id);
        return ResponseEntity.ok().body("l'utilisateur d'id {"+id+"} à été supprimé");
    }
}
