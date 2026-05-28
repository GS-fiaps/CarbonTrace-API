package br.com.fiap.carbontrace.validation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    public record ApiErrorResponse(Integer status, String message) {
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiErrorResponse> handle(ResponseStatusException exception) {

        log.warn("Erro na requisição: {}", exception.getReason());

        ApiErrorResponse response = new ApiErrorResponse(
                exception.getStatusCode().value(),
                exception.getReason()
        );

        return ResponseEntity.status(exception.getStatusCode()).body(response);
    }
}