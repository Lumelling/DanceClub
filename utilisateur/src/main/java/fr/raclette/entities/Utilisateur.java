package fr.raclette.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "utilisateurs")
public class Utilisateur {
    @Id
    private Long id;

    @Field
    private String nom;

    @Field
    private String prenom;

    @Field
    private String pseudo;

    @Field
    private String mdp;

    @Field
    private String ville;

    @Field
    private String codePostal;

    @Field
    private String rue;

    @Field
    private int expertise;

    @Field
    private String role;
}
