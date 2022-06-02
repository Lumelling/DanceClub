package fr.raclette.services;


import fr.raclette.entities.Utilisateur;

import java.util.Optional;

public interface UtilisateurService {
    Utilisateur inscriptionUser(Utilisateur user);
    void deleteUser(long user);
    Iterable<Utilisateur> findAllUsers();
    Optional<Utilisateur> findUser(long id);
    Utilisateur updateUser(Utilisateur id);
    Boolean RoleExist(String role);
}
