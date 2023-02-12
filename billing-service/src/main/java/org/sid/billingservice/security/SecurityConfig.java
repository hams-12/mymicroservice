package org.sid.billingservice.security;

import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@KeycloakConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends KeycloakWebSecurityConfigurerAdapter {
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //là ou on va chercher les utilisateurs et leurs roles. Ici on dit à Spring qu'on va le déléguer à keycloak
        auth.authenticationProvider(keycloakAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        //on va faire une authentification stateless donc il faut utiliser csrf
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/h2-console/**").permitAll();//Autoriser les requetes vers la base de données h2
        //http.authorizeRequests().antMatchers("/fullBill/**").hasAuthority("USER");
        http.headers().frameOptions().disable(); //autoriser l'affichage des frames de h2console
        http.authorizeRequests().anyRequest().authenticated(); //signifie que toutes les requetes necessitent l'authentification
    }
}
