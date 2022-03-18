package fr.raclette.services;

import fr.raclette.entities.Utilisateur;
import fr.raclette.repo.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    /**
     * Inscription d'un utilisateur
     * @return l'utilisateur créé
     */
    public Utilisateur inscriptionUser(Utilisateur user){
        return utilisateurRepository.save(user);
    }

    @Override
    /**
     * suppression d'un utilisateur
     */
    public void deleteUser(Utilisateur user) {
        utilisateurRepository.delete(user);
    }

    @Override
    /**
     * récupération de tous les utilisateurs
     * @return liste des Utilisateurs en JSON. [] si aucun compte.
     */
    public Iterable<Utilisateur> findAllUsers() {
        return utilisateurRepository.findAll();
    }

    @Override
    /**
     * récupération d'un utilisateur
     * @return l'utilisateur avec son id.
     */
    public Optional<Utilisateur> findUser(long id) {
        return utilisateurRepository.findById(id);
    }


}
