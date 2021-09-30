package com.example.user.adapter.factory;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.user.model.entity.User;
import org.example.user.port.factory.TokenFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class TokenJwtFactory implements TokenFactory {

    private final String secret;
    private final String issuer;
    private final Long duration;

    public TokenJwtFactory(@Value("${jwt.secret}") String secret, @Value("${jwt.issuer}") String issuer, @Value("${jwt.duration}") int duration) {
        this.secret = secret;
        this.issuer = issuer;
        this.duration = duration * 60000L;
    }

    @Override
    public String create(User user) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = secret.getBytes(StandardCharsets.UTF_8);

        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        return Jwts.builder()
                .setIssuedAt(now)
                .setSubject(user.getEmail().getValue())
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey)
                .setExpiration(new Date(nowMillis + duration))
                .compact();
    }
}
