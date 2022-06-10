package fr.raclette.dto;

import lombok.Data;

@Data
public class Utilisateur {
    private long id;
    private String role;
    private int expertise;
}
