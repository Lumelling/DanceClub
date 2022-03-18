package fr.raclette.services;


import fr.raclette.entities.Utilisateur;

import java.util.Optional;

public interface UtilisateurService {
    Utilisateur inscriptionUser(Utilisateur user);
    void deleteUser(Utilisateur user);
    Iterable<Utilisateur> findAllUsers();
    Optional<Utilisateur> findUser(long id);
    Utilisateur updateExpertise(String expertise);
    public Utilisateur updateExpertise(int expertise, long id);
}
