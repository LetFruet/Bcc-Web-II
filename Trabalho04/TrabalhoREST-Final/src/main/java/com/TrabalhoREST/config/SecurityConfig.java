package com.TrabalhoREST.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // Desabilite o CSRF se estiver testando com POSTMAN, por exemplo
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/public/").permitAll()  // Permitir acesso público a algumas rotas
                .requestMatchers("/api/admin/").hasRole("ADMIN")  // Proteger rotas por roles
                .requestMatchers("/api/user/").hasRole("USER")
                .anyRequest().authenticated()  // Qualquer outra requisição precisa de autenticação
            )
            .formLogin().and()
            .httpBasic();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .roles("USER")
            .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("password")
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
