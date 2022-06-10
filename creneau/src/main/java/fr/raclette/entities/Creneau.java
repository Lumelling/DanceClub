package fr.raclette.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
public class Creneau {
    @Id
    private Long id;

    @Field
    private String date;

    @Field
    private int heure;
}
