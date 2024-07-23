package com.example.test.controllers;

import com.example.test.entity.User;
import com.example.test.repositories.UserRepository;
import com.example.test.response.BaseResponse;
import com.example.test.rest.IAuthentication;
import com.example.test.services.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.time.Instant;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthenticationController implements IAuthentication {

    private final JwtEncoder encoder;
    private final UserRepository userRepository;
    private final UserService userSuervice;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ResponseEntity<BaseResponse> login( @RequestBody User user ) {
        Optional<User> request = userRepository.findByLogin(user.getUsername());
        if ( request.isPresent() && userSuervice.checkUserPassword( user.getPassword(), request.get().getPassword())){
            String token = generateToken(user);
            HttpHeaders httpHeaders = new HttpHeaders();
            //httpHeaders.set("X-AUTH-TOKEN", token);
            return ResponseEntity.ok()
                                 .headers(httpHeaders)
                                 .contentType(MediaType.APPLICATION_JSON)
                                 .body( new BaseResponse(200,"success", token ));
        }  else {
            return ResponseEntity.status( HttpStatus.UNAUTHORIZED )
                                 .contentType( MediaType.APPLICATION_JSON )
                                 .body( new BaseResponse(401,"Invalid username or password" ));
        }               
    }

    private String generateToken(User user) {
        Instant now = Instant.now();
        long expiry = 6000L; // five minutes
        JwtClaimsSet claims = JwtClaimsSet.builder()
                                          .issuer( "self" )
                                          .issuedAt( now )
                                          .expiresAt( now.plusSeconds( expiry ))
                                          .subject( user.getUsername() )
                                          .build();
        return this.encoder.encode( JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
