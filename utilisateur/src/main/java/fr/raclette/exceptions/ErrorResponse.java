package fr.raclette.exceptions;

import java.util.List;

class ErrorResponse {
    private final String message;
    private final String code;          // code for frontend to tell which message it needs to show for user
    private final List<Object> details; // ie. details which field in input failed

    ErrorResponse(String message, String code, List<Object> details) {
        this.message = message;
        this.code = code;
        this.details = details;
    }

    // ... constructors, builders, getters, setters, equals, hashcode, toString ... but I prefer to use Lombok instead ;)
}