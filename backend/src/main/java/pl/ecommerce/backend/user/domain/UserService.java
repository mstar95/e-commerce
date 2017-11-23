package pl.ecommerce.backend.user.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.ecommerce.backend.payment.domain.PaymentFacade;

@RequiredArgsConstructor
class UserService {

    private final UserRepository userRepository;
    private final PaymentFacade paymentFacade;
    private final PasswordEncoder passwordEncoder;

    Long createUser(User user) {
        encodePassword(user);
        Long userId = userRepository.save(user).getId();
        paymentFacade.createWallet(userId);
        return userId;
    }

    private void encodePassword(User user) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
    }

}
