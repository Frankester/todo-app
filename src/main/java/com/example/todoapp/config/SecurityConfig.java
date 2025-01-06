package com.example.todoapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    private Environment env;

    private static String CLIENT_PROPERTY_KEY
            = "spring.security.oauth2.client.registration.";
    private static String ERROR_MESSAGE = "Falta colocar el {} en archivo de configuracion!!";

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(){
        ClientRegistration clientRegistration = this.getRegistration();
        if(clientRegistration != null){
            return new InMemoryClientRegistrationRepository(clientRegistration);
        } else {
            if(log.isErrorEnabled()){
                log.error("El client secret y/o el client id del application.properties no son validos");
            }
            return null;
        }
    }


    private ClientRegistration getRegistration() {
        String clientId = env.getProperty(
                CLIENT_PROPERTY_KEY+"google.client-id"
        );
        if(clientId == null){
            if(log.isErrorEnabled()){
                log.error(ERROR_MESSAGE.replace("{}","client-id"));
            }
            return  null;
        }

        String clientSecret = env.getProperty(
                CLIENT_PROPERTY_KEY+"google.client-secret"
        );

        if(clientSecret == null){
            if(log.isErrorEnabled()){
                log.error(ERROR_MESSAGE.replace("{}","client-secret"));
            }
            return  null;
        }

        return ClientRegistration.withRegistrationId("google")
                .clientId(clientId)
                .clientSecret(clientSecret)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                .scope("openid","profile","email","phone")
                .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
                .tokenUri("https://www.googleapis.com/oauth2/v4/token")
                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
                .userNameAttributeName(IdTokenClaimNames.SUB)
                .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
                .clientName("Google")
                .build();
    }

    @Bean
    public SecurityFilterChain chain(HttpSecurity http) throws Exception {
        return http
                .cors().and()
                .csrf().disable()
                .authorizeHttpRequests(authorize ->
                        authorize
                                .anyRequest().authenticated()
                ).oauth2Login(Customizer.withDefaults())
                .build();
    }


}
