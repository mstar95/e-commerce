package pl.ecommerce.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.ecommerce.backend.time.domain.TimeManager;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
class JwtService {

    @Autowired
    private TimeManager timeManager;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    String generateToken(String subject) {
        Map<String, Object> claims = new HashMap<>();
        final LocalDateTime createdDate = timeManager.getCurrentDate();
        final LocalDateTime expirationDate = calculateExpirationDate(createdDate);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setExpiration(timeManager.localDateTimeToDate(expirationDate))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(timeManager.getCurrentTimestamp());
    }

    private LocalDateTime calculateExpirationDate(LocalDateTime createdDate) {
        return createdDate.plusMinutes(expiration);
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }


    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }


}

