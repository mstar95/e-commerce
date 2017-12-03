package pl.ecommerce.backend.user.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.ecommerce.backend.payment.domain.PaymentFacade;
import pl.ecommerce.backend.user.exceptions.CreateUserException;
import pl.ecommerce.backend.user.exceptions.FindUserException;

@RequiredArgsConstructor
class UserService {

    private final UserRepository userRepository;
    private final PaymentFacade paymentFacade;
    private final PasswordEncoder passwordEncoder;

    Long createUser(User user) {
        encodePassword(user);
        checkNameAndLogin(user);
        Long userId = userRepository.save(user).getId();
        paymentFacade.createWallet(userId);
        return userId;
    }

    String getCurrentUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    Long getCurrentUserId() {
        String name = getCurrentUserName();
        return userRepository.getUserByName(name)
                .orElseThrow(() -> new FindUserException("There is no uesr with name: "  + name))
                .getId();
    }

    private void checkNameAndLogin(User user) {
        userRepository.getUserByNameOrEmail(user.getName(), user.getEmail())
                .ifPresent((u) -> {
                    if (user.getEmail().equals(u.getEmail())) {
                        throw new CreateUserException("User with current email exist in system");
                    } else {
                        throw new CreateUserException("User with current name exist in system");
                    }
                });
    }

    private void encodePassword(User user) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
    }
}
