package org.example.user.service;

import org.example.user.model.entity.Password;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public final class HashingPasswordService {

    private static final MessageDigest MESSAGE_DIGEST;
    private static final Base64.Encoder BASE64;

    static {
        try {
            MESSAGE_DIGEST = MessageDigest.getInstance("SHA-256");
            BASE64 = Base64.getEncoder();
        } catch (NoSuchAlgorithmException e) {
            throw new HashInitializationException(e);
        }
    }

    private HashingPasswordService(){}

    public static Password calculate(Password password) {
        byte[] hash = MESSAGE_DIGEST.digest(password.getValue().getBytes(StandardCharsets.UTF_8));
        return Password.ofHash(BASE64.encodeToString(hash));
    }

    public static class HashInitializationException extends RuntimeException {
        private HashInitializationException(NoSuchAlgorithmException e) {
            super("Message digest initialization error", e);
        }
    }
}
