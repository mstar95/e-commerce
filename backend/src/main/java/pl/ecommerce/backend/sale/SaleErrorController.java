package pl.ecommerce.backend.sale;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.ecommerce.backend.sale.exceptions.SaleFindException;
import pl.ecommerce.backend.user.exceptions.CreateUserException;

@RestControllerAdvice
class SaleErrorController {

    @ExceptionHandler(value = { SaleFindException.class })
    public ResponseEntity<String> createUserException(Exception ex) {
        return ResponseEntity.unprocessableEntity().body(ex.getMessage());
    }

}
