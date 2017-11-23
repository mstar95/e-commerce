package pl.ecommerce.backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
class UserDetailsServiceImpl implements UserDetailsService {

    private final UserCredentialsRepository userCredentialsRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserCredentials userCredentials = userCredentialsRepository.getUserCredentialsByName(username);
        return new User(userCredentials.getName(), userCredentials.getPassword(), Collections.EMPTY_LIST);
    }
}
