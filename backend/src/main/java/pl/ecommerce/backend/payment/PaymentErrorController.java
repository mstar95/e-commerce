package pl.ecommerce.backend.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.ecommerce.backend.payment.exceptions.CreateWalletException;
import pl.ecommerce.backend.payment.exceptions.FindWalletException;
import pl.ecommerce.backend.payment.exceptions.ReducePointsException;

@RestControllerAdvice
class PaymentErrorController {

    @ExceptionHandler(value = { CreateWalletException.class,
            FindWalletException.class,
            ReducePointsException.class})
    public ResponseEntity<String> paymentException(Exception ex) {
        return ResponseEntity.unprocessableEntity().body(ex.getMessage());
    }
}
