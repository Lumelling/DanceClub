package fr.raclette.exceptions;

enum ErrorCode {
    EXPERTISE_NOT_CORRECT("expertise_not_correct");

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}