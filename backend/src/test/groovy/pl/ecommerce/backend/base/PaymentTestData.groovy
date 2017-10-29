package pl.ecommerce.backend.base

import pl.ecommerce.backend.payment.dtos.ChargePointsDto
import pl.ecommerce.backend.payment.dtos.ReducePointsDto


class PaymentTestData {
    static final chargePointsDto0 = ChargePointsDto.builder()
            .userId(UserTestData.USER_ID_1)
            .amount(new BigDecimal(100))
            .build()
    static final reducePointsDto0 = ReducePointsDto.builder()
            .userId(UserTestData.USER_ID_1)
            .amount(new BigDecimal(100))
            .build()
}
