package fr.raclette.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
class UtilisateursExceptionHandler extends HttpResponseExceptionHandler {

    @ExceptionHandler(value = {ExpertiseNotCorrect.class})
    ResponseEntity<ErrorResponse> handleCustomerAlreadyExists(ExpertiseNotCorrect e) {
        Map<String, String> detailsMap = Collections.singletonMap("employeeId", e.getMessage());
        return getErrorResponseEntity(
                e,
                ErrorCode.EXPERTISE_NOT_CORRECT.getCode(),
                Collections.singletonList(detailsMap),
                HttpStatus.NOT_FOUND);
    }
}
