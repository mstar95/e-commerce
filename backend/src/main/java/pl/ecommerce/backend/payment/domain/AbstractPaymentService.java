package pl.ecommerce.backend.payment.domain;

import lombok.RequiredArgsConstructor;
import pl.ecommerce.backend.payment.exceptions.CreateWalletException;
import pl.ecommerce.backend.payment.exceptions.ReducePointsException;

import java.math.BigDecimal;
import java.util.function.Consumer;
import java.util.function.Supplier;

@RequiredArgsConstructor
abstract class AbstractPaymentService {

    protected final WalletRepository walletRepository;

    protected BigDecimal substractPoints(BigDecimal reducePoints, Supplier<BigDecimal> getter,
                                       Consumer<BigDecimal> setter, Long id) {
        BigDecimal points = getter.get().subtract(reducePoints);
        if (points.signum() == -1) {
            throw new ReducePointsException(getter.get(), reducePoints, id);
        }
        setter.accept(points);
        return points;
    }

    protected BigDecimal addPoints(BigDecimal chargePoints, Supplier<BigDecimal> getter, Consumer<BigDecimal> setter) {
        BigDecimal result = getter.get().add(chargePoints);
        setter.accept(result);
        return result;
    }

    protected void throwCreateWalletException(String s) {
        throw new CreateWalletException(s);
    }
}
