package fr.raclette.exceptions;

public class ExpertiseNotCorrect extends Exception {
    public ExpertiseNotCorrect(String errorMessage) {
        super(errorMessage);
    }
}