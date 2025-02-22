package com.example.Insurance.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.Insurance.Entity.Customer;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTtokenservice {

    @Value("${jwt.algorithm.key}")
    private String algorithmkey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry}")
    private String expiryTimeStr; // Store as String to avoid type issues

    private int expirytime;
    private Algorithm algorithm;

    @PostConstruct
    public void postconstruct() {
        System.out.println("Algorithm key: " + algorithmkey); // Debugging
        if (algorithmkey == null || algorithmkey.isEmpty()) {
            throw new IllegalStateException("Algorithm key is missing!");
        }
        this.expirytime = Integer.parseInt(expiryTimeStr); // Convert to int
        this.algorithm = Algorithm.HMAC256(algorithmkey.getBytes());
    }

    public String generatetoken(Customer customer) {
        return JWT.create()
                .withIssuer(issuer)
                .withSubject(customer.getUsername()) // Store username in token
                .withExpiresAt(new Date(System.currentTimeMillis() + expirytime))
                .sign(algorithm);
    }
}
