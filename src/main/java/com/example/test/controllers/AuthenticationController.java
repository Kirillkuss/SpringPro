package com.example.test.controllers;

import com.example.test.entity.User;
import com.example.test.response.BaseResponse;
import com.example.test.rest.IAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthenticationController implements IAuthentication {

    private final UserDetailsService userDetailsService;
    private final JwtEncoder encoder;

    public ResponseEntity<BaseResponse> login( @RequestBody User user ) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        if ( user.getPassword().equalsIgnoreCase( userDetails.getPassword() )) {
            String token = generateToken(userDetails);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("X-AUTH-TOKEN", token);
            return ResponseEntity.ok()
                                 .headers(httpHeaders)
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .body( new BaseResponse(200,"success", token ));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .body( new BaseResponse(401,"Invalid username or password" ));
        }
    }

    private String generateToken(UserDetails userDetails) {
        Instant now = Instant.now();
        long expiry = 600L; // five minutes
        String scope = userDetails.getAuthorities()
                                  .stream()
                                  .map( GrantedAuthority::getAuthority )
                                  .collect( Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                                          .issuer( "self" )
                                          .issuedAt( now )
                                          .expiresAt( now.plusSeconds( expiry ))
                                          .subject( userDetails.getUsername() )
                                          .claim( "scope", scope )
                                          .build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
