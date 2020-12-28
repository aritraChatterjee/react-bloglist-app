package edu.aritra.bloglist.security;

import edu.aritra.bloglist.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Optional;

@Component
public class SecurityUtil {
    private Key key;

    public SecurityUtil(@Value("${jwt.secret}") String secret) {
        byte[] decodedKey = Base64.getDecoder().decode(secret);
        key = new SecretKeySpec(decodedKey, 0, decodedKey.length, SignatureAlgorithm.HS512.getJcaName());
    }

    public Optional<UserDto> parseToken(String token) {
        try {
            Claims body = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            UserDto u = new UserDto();
            u.setUsername(body.getSubject());
            u.setId((String) body.get("userId"));

            return Optional.of(u);

        } catch (JwtException | ClassCastException e) {
            return Optional.empty();
        }
    }


    public String generateToken(String username, String id) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("userId", id + "");

        return Jwts.builder()
                .setClaims(claims)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

}
