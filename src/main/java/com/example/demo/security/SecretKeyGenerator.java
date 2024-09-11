
package com.example.demo.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

public class SecretKeyGenerator {

    /*public static String generateSecretKey(int length) {
        byte[] key = new byte[length];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }

    public static void main(String[] args) {
        String secretKey = generateSecretKey(32); // Genera una clave secreta de 32 bytes
        System.out.println("Clave secreta JWT: " + secretKey);
    }

     */

    public static void main(String[] args) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("Clave secreta JWT: " + base64Key);
    }
}
