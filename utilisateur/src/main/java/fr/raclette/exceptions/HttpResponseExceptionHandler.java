package fr.raclette.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

public abstract class HttpResponseExceptionHandler {
    protected ResponseEntity<ErrorResponse> getErrorResponseEntity(
        Exception e,
        String errorCode,
        List<Object> details,
        HttpStatus status) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), errorCode, details);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.set("Content-type", MediaType.APPLICATION_JSON_VALUE);
        return new ResponseEntity<>(errorResponse, headers, status);
    }
}
