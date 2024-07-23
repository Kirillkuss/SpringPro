package com.example.test.config.security;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.SecurityFilterChain;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration  {

    @Value("${jwt.public.key}")
    private RSAPublicKey publicKey;

    @Value("${jwt.private.key}")
    private RSAPrivateKey privateKey;

    @Bean
    public SecurityFilterChain filterChain( HttpSecurity http ) throws Exception {
        return http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                    .antMatchers( "/auth/**", "/test/**", "/swagger-ui-custom.html", "/swagger-ui.html", "/swagger-ui/**",
                    "/swagger-ui/index.html", "/", "/web/api", "/api/**", "/images/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
                    .csrf(csrf -> csrf.disable())
                    .formLogin(login -> login.disable())
                    .httpBasic(basic -> basic.disable())
                    .sessionManagement(mng -> mng.sessionCreationPolicy( SessionCreationPolicy.STATELESS ))
                    .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                    .exceptionHandling( ex -> ex.authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                                                .accessDeniedHandler(new BearerTokenAccessDeniedHandler())
                                                .and())
                    .build();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(this.publicKey).build();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder( new ImmutableJWKSet<>( new JWKSet( new RSAKey.Builder( this.publicKey )
                                                                                  .privateKey( this.privateKey )
                                                                                  .build() )));
    }
    /**
     * Encode for user
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
