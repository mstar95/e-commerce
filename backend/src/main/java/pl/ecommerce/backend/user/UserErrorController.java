package pl.ecommerce.backend.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.ecommerce.backend.user.exceptions.CreateUserException;

@RestControllerAdvice
public class UserErrorController {

    @ExceptionHandler(value = { CreateUserException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> createUserException(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
