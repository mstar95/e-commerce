package pl.ecommerce.backend.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.ecommerce.backend.user.exceptions.CreateUserException;

@RestControllerAdvice
public class UserErrorController {

    @ExceptionHandler(value = { CreateUserException.class })
    public ResponseEntity<String> createUserException(Exception ex) {
        return ResponseEntity.unprocessableEntity().body(ex.getMessage());
    }

}
