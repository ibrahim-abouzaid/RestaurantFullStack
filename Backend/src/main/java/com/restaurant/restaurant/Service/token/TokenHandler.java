package com.restaurant.restaurant.Service.token;

import com.restaurant.restaurant.DTO.security.UserDto;
import com.restaurant.restaurant.Service.security.UserService;
import com.restaurant.restaurant.setting.JWTToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class TokenHandler {

    private String secretKey;
    private Duration time;
    private JwtBuilder jwtBuilder;
    private JwtParser  jwtParser;
    @Autowired
    private UserService  userService;

     public TokenHandler(JWTToken  jwtToken){
         this.secretKey=jwtToken.getSecret();
         this.time=jwtToken.getTime();
         Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
         this.jwtBuilder = Jwts.builder().signWith(key);
         this.jwtParser =Jwts.parserBuilder().setSigningKey(key).build();
     }

     public String createToken(UserDto userDto){
         Date issueDate=new Date();
         Date expirationDate =Date.from(issueDate.toInstant().plus(this.time));
         //add username and issue date and expiration date for this token
         jwtBuilder.setSubject(userDto.getUsername()).setIssuedAt(issueDate).setExpiration(expirationDate);
         jwtBuilder.claim("roles",userDto
                                        .getRoles()
                                        .stream()
                                        .map(roleDto -> roleDto.getRole())
                                        .collect(Collectors.toList()));

         return jwtBuilder.compact();
     }

     public UserDto validateToken(String token){
         if(jwtParser.isSigned(token)){
             // JWt has header - body - signature
             //so we get the body which has all information about the user
             Claims claims =jwtParser.parseClaimsJws(token).getBody();
             String username=claims.getSubject();
             Date issueDate=claims.getIssuedAt();
             Date expirationDate=claims.getExpiration();

             UserDto userDto= userService.getUserByUsername(username);
             Boolean isValidToken = Objects.nonNull(userDto)
                     && expirationDate.after(new Date())
                     && issueDate.before(expirationDate);

             if(!isValidToken){
                 return null;
             }
             //finally get user date from the token and use it ;
             return userDto;
         }
         return null;
     }

}
