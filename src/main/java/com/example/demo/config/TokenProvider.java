package com.example.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {

    private static final String AUTHORITIES_KEY = "auth";
    private static final String SECRET = "28TpkVLEpleJAhA5FDPWlJlncIEyiJ0BfUNVJVpmxPNk58yjZ2zr/m6bYEOaCDbnucsozEttG6vxnaklfgFnWS2AzYxP19c7WJJSKTLP58lRvv7+ueJWgLSXGQTwgHqIqWqRNsBAkZfkhNWsPpnZYopgCelefYnR1QwBcofYMnBuZLyOodjimd4QOU55WJExsncyM5zhuXkXYxfByOz8hybU3HonxdPn/BUtFA==";

    private static final Key KEY = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET));

    public String createToken(final Authentication authentication) {
        Objects.requireNonNull(authentication, "Authentication must be provided to create token");
        final String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return generateToken(authentication.getName(), authorities);
    }

    public Authentication getAuthentication(final String token) {
        Objects.requireNonNull(token, "Token must be provided to create authentication");
        final Claims claims = getAllClaimsFromToken(token);

        final Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .filter(Strings::isNotEmpty)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
// TODO
//        final String controlKey = claims.get(CONTROL_KEY).toString();
//        final var expectedControlKey = controlKeyProvider.getKey(claims.getSubject());
//        final boolean access = Objects.equals(expectedControlKey, controlKey);

        final User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    private Claims getAllClaimsFromToken(final String token) {
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }

    private String generateToken(final String subject, final String authorities) {
        //TODO: remove date
        final Date now = new Date();
        return Jwts.builder()
                .setSubject(subject)
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(KEY, SignatureAlgorithm.HS512)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofDays(360).toMillis()))
                .compact();
    }
}

