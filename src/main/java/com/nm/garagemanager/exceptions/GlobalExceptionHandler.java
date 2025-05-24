package com.nm.garagemanager.exceptions;



import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<ErrorMessageDTO> errors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            ErrorMessageDTO dto = new ErrorMessageDTO();
            dto.setError(error.getField());
            dto.setMessage(message);

            errors.add(dto);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorMessageDTO> handleInvalidJson(HttpMessageNotReadableException ex) {
        ErrorMessageDTO error = new ErrorMessageDTO();
        error.setError("Invalid JSON");
        error.setMessage("O JSON enviado está mal formado. Verifique os valores dos campos.");
        return ResponseEntity.badRequest().body(error);
    }






     @ExceptionHandler(PlateStatusNotFoundException.class)
    public ResponseEntity<ErrorMessageDTO> handlePlateNotFoundException(PlateStatusNotFoundException ex) {
        ErrorMessageDTO error = new ErrorMessageDTO();
        error.setError("Not Found");
        error.setMessage(ex.getMessage());


        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

 @ExceptionHandler(SpotNotFoundforlatlngException.class)
    public ResponseEntity<ErrorMessageDTO> handleSpotNotFoundException(SpotNotFoundforlatlngException ex) {
        ErrorMessageDTO error = new ErrorMessageDTO();
        error.setError("Not Found");
        error.setMessage(ex.getMessage());


        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
