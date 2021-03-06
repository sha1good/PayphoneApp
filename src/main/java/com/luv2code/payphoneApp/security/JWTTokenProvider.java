package com.luv2code.payphoneApp.security;

import com.luv2code.payphoneApp.model.User;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.luv2code.payphoneApp.security.SecurityConstants.EXPIRATION_TIME;
import static com.luv2code.payphoneApp.security.SecurityConstants.SECRET;

@Component
public class JWTTokenProvider {

    //Generate a Token
    public String generateToken(Authentication authentication){

        User user = (User)authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());

        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        String userId = Long.toString(user.getId());

        Map<String, Object>  claims = new HashMap<>();
          claims.put("id",(Long.toString(user.getId())));
          claims.put("fullName",user.getFullName());
          claims.put("username", user.getUsername());

          return Jwts.builder().setSubject(userId).setClaims(claims)
                  .setIssuedAt(now).setExpiration(expiryDate)
                  .signWith(SignatureAlgorithm.HS512, SECRET).compact();

    }


    //Validate the token
    public boolean validateToken(String token) {
        try {

            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;

        } catch (SignatureException ex) {
            System.out.println("Invalid Signature Supplied !");
        }catch(MalformedJwtException ex) {
            System.out.println(" Invalid JWT Token");
        }catch(ExpiredJwtException ex) {
            System.out.println("Token has Expired!");
        }catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported Token Supplied");
        }catch(IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty");
        }
        return false;


    }


    //Get User Id from JWT
    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        String id =  (String) claims.get("id");
        return Long.parseLong(id);
    }

}
