package fr.raclette.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "cours")
public class Cours {

    @Id
    private Long id;

    @Field
    private String titre;

    @Field
    private int niveau;

    @Field
    private Creneau creneau;

    @Field
    private Long idEnseignant;

    @Field
    private String lieu;

    @Field
    private int duree;
}
