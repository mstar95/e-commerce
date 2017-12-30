package pl.ecommerce.backend.sale;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.ecommerce.backend.sale.domain.SaleFacade;

@Component
@RequiredArgsConstructor
class AuctionScheduledTasks {

    private final SaleFacade saleFacade;

    @Scheduled(fixedRate = 5000)
    public void finalizeAuctions() {
        saleFacade.finalizeAuctions();
    }
}
