package pl.ecommerce.backend.payment.exceptions;

public class FindWalletException extends RuntimeException {
    public FindWalletException(Long userId) {
        super("There is no wallet for user with id:" + userId);
    }
}
