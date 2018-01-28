package pl.ecommerce.backend.base

import pl.ecommerce.backend.message.dto.CreateFinalizeSaleMessageDto
import pl.ecommerce.backend.user.dto.UserOutDto


class MessageTestData {
    static final finalizePaymentDto = CreateFinalizeSaleMessageDto.builder()
            .amount(BigDecimal.TEN).buyerId(UserTestData.USER_ID_1)
            .productName(SaleTestData.createSaleDto1.name)
            .sellerId(UserTestData.USER_ID_2).build()

    static final QUERY_USER_1 = new UserOutDto("user1", BigDecimal.TEN)
    static final QUERY_USER_2 = new UserOutDto("user2", BigDecimal.TEN)

    static final FinalizeSaleSellerMessage = "Uzytkownik {0} kupil od Ciebie produkt {1} za {2}"
    static final FinalizeSaleSellerMessageResult =
            "Uzytkownik user1 kupil od Ciebie produkt Yeezy za 10"
}
