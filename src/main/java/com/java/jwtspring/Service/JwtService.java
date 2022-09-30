package com.java.jwtspring.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private static String secretKey="ibrahim-jwt";

    public String generateToken(String username){
        return Jwts.builder().setSubject(username)
                .setIssuer("ibrahim").setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+10*60*1000))
                .signWith(SignatureAlgorithm.HS256,secretKey).compact();
    }

    public boolean validateToken(String token){
        if(usernameToken(token)!=null && isExpired(token)) {
            return true;
        }
     return false;
    }

    public String usernameToken(String token){
        Claims claims=extractClaims(token);
        return claims.getSubject();
    }


    public boolean isExpired(String token){
        Claims claims=extractClaims(token);
        return claims.getExpiration().after(new Date(System.currentTimeMillis()));
    }

    private Claims extractClaims(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
}
