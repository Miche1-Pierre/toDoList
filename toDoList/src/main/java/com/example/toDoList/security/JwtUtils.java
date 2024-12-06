package com.example.toDoList.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

@Service
public class JwtUtils {

    public String generationToken(String pseudo) {

        return Jwts.builder()
                .setSubject(pseudo)
                .signWith(
                        SignatureAlgorithm.HS256,
                        "azerty")
                .compact();
    }

    public String getPseudoFromJwt(String jwt) {

        return Jwts.parser()
                .setSigningKey("azerty")
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }
}
