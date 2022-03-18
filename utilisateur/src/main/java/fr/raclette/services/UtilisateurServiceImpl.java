package fr.raclette.services;

import fr.raclette.entities.Utilisateur;
import fr.raclette.repo.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public Utilisateur inscriptionUser(Utilisateur user){
            return utilisateurRepository.save(user);
    }

    @Override
    public void deleteUser(Utilisateur user) {
        utilisateurRepository.delete(user);
    }

    public Iterable<Utilisateur> findAllUsers() {
        return utilisateurRepository.findAll();
    }
}
