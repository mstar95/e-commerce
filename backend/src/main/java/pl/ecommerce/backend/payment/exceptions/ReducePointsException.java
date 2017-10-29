package pl.ecommerce.backend.payment.exceptions;

import pl.ecommerce.backend.payment.dtos.ReducePointsDto;

import java.math.BigDecimal;

public class ReducePointsException extends RuntimeException{
    public ReducePointsException(BigDecimal points, ReducePointsDto reducePointsDto){
        super("cant sustract " + points
                + " and " +  reducePointsDto.getAmount() + " for user:" + reducePointsDto.getUserId());
    }
}
