package br.com.boletojuros.adapter.http.exceptions;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.boletojuros.core.exceptions.ApplicationException;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> applicationException(ApplicationException ex, WebRequest request) {
        var response = ErrorResponse.builder()
                .messages(Arrays.asList(ex.getMessage()))
                .erro(ex.getTipoException().toString().toLowerCase())
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Date())
                .path(request.getContextPath())
                .build();
        
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        // TODO Auto-generated method stub
        var erros = ex.getFieldErrors().stream()
                .map(error -> error.getField() + " " + error.getDefaultMessage())
                .collect(Collectors.toList());

        var response = ErrorResponse.builder()
                .messages(erros)
                .erro("erro_na_validao_dto")
                .status(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Date())
                .path(request.getContextPath())
                .build();
        
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
