package pl.ecommerce.backend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    private static String URL = "/auth";

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtService jwtService;

    JwtLoginFilter(AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(URL));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {
        AuthenticationCredentials creds = new ObjectMapper()
                .readValue(req.getInputStream(), AuthenticationCredentials.class);
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(creds.getUsername(),
                        creds.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        String jwt = jwtService.generateToken(auth.getName());
        res.addHeader(tokenHeader, jwt);
    }
}