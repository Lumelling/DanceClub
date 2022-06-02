package fr.raclette.exposition;

import fr.raclette.dto.Utilisateur;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "utilisateur")
public interface UtilisateurFeignController {

    @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = "application/json")
    Utilisateur get(@PathVariable long id);
}
