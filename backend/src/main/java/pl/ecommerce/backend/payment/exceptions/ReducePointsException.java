package pl.ecommerce.backend.payment.exceptions;

import java.math.BigDecimal;

public class ReducePointsException extends RuntimeException{
    public ReducePointsException(BigDecimal minuend, BigDecimal substrahend, Long userId){
        super("cant substract " + minuend
                + " and " +  substrahend + " user:" + userId);
    }
}
