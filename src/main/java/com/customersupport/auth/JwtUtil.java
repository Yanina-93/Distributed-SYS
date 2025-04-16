/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.customersupport.auth;

/**
 *
 * @author yani_
 */
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

public class JwtUtil {
    
    private static final String SECRET_KEY = "my_key_secure";
    
    public static boolean vakidateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch(JWTVerificationException e){
            System.err.println("X JWT invalid: "+ e.getMessage());
            return false;
        }
    }
    
    public static String generateToken(String userId){
        return JWT.create()
                .withSubject(userId)
                .sign(Algorithm.HMAC256(SECRET_KEY));
    }
    
}
