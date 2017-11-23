package pl.ecommerce.backend.payment.exceptions;

public class FindWalletException extends RuntimeException {
    public FindWalletException(Long userId) {
        super("There is no wallet for pl.ecommerce.backend.user with id:" + userId);
    }
}
