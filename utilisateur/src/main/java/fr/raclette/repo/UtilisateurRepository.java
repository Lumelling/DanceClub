package fr.raclette.repo;

import fr.raclette.entities.Utilisateur;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Repository de gestion des comptes en banque
 */
@Component
public interface UtilisateurRepository extends MongoRepository<Utilisateur,Long> {

}
