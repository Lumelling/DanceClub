package fr.raclette.services;


import fr.raclette.entities.Utilisateur;

public interface UtilisateurService {
    Utilisateur inscriptionUser(Utilisateur client);
    void deleteUser(Utilisateur client);
    Iterable<Utilisateur> findAllUsers();
}
