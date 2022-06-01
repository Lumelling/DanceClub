package fr.raclette.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;


public class Creneau {
    @Id
    private Long id;

    @Field
    private Date date;

    @Field
    private int heure;
}
