package pl.ecommerce.backend.base

import pl.ecommerce.backend.payment.dtos.ChargePointsDto
import pl.ecommerce.backend.payment.dtos.LockPointsDto
import pl.ecommerce.backend.payment.dtos.ReducePointsDto
import pl.ecommerce.backend.payment.dtos.TransferPointsDto

class PaymentTestData {
    static final chargePointsDto0 = ChargePointsDto.builder()
            .userId(UserTestData.USER_ID_1)
            .amount(new BigDecimal(100))
            .build()
    static final transferPointsDto0 = TransferPointsDto.builder()
            .fromId(UserTestData.USER_ID_1)
            .toId(UserTestData.USER_ID_2)
            .amount(new BigDecimal(50))
            .build()
    static final reducePointsDto0 = ReducePointsDto.builder()
            .userId(UserTestData.USER_ID_1)
            .amount(new BigDecimal(100))
            .build()

    static final lockPointsDto0 = LockPointsDto.builder()
            .userId(UserTestData.USER_ID_1)
            .amount(new BigDecimal(50))
            .build()
}
