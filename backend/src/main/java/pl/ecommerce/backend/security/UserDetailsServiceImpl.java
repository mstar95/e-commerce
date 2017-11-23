package pl.ecommerce.backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.ecommerce.backend.user.domain.UserFacade;

@Component
@RequiredArgsConstructor
class UserDetailsServiceImpl implements UserDetailsService {

    private final UserFacade userFacade;

    @Override
    public UserDetails loadUserByUsername(String username) {
       return new UserDetailsImpl() ;

    }
}
