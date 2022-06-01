package fr.raclette.repo;

import fr.raclette.entities.Cours;
import fr.raclette.entities.Creneau;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

/**
 * Repository de gestion des cours
 */
@Component
public interface CreneauRepository extends MongoRepository<Cours,Long> {

}
