package com.test.chat.jwt;

import com.test.chat.models.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String Secret_Key = "6756440b4ca46d729142068de20ebe874988d9fc3f2ce8d10d026b4beeae6e93abb801b78387640de995c8eb4611e0a77691ae42daa6eda8a6db3b3a94a86dea";

    public String generateToken(Users user){
        return generateToken(new HashMap<>(), user);
    }
    public String generateToken(Map<String, Object> extraClaims,
                                Users user){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getEmail())
                .setIssuedAt( new Date(System.currentTimeMillis()))
                .setExpiration( new Date(System.currentTimeMillis() + 1000*60*60*24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public String extractUserName(String token){
        return extractClaims(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, Users user){
        final String userName = extractUserName(token);
        return (userName.equals(user.getEmail()) && !isTokenExpired(token));
    }
    public <T> T extractClaims(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token){
        return extractClaims(token, Claims::getExpiration);
    }
    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(Secret_Key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


}
