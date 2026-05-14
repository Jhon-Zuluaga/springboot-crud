package com.hibernate.ferreteria.Security;

import com.hibernate.ferreteria.Services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Clase de configuracion de seguridad de Spring
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;

    public SecurityConfig(UserService userService){
        this.userService = userService;
    }

    // Encripta contraseñas con Bcrypt (hashing saguro con salt automatico)
    @Bean
    public PasswordEncoder codifyPass(){
        return new BCryptPasswordEncoder();
    }

    // Conecta UserService y BCrypt para que Spring sepa como autenticar usuarios
    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);      // Donde buscar el usuario (La bd)
        provider.setPasswordEncoder(codifyPass());         // Como verificar la contraseña (Bcrypt)
        return provider;
    }

    // Expone el AuthtenticationManager como Bean (valida credenciales de usuarios)
    @Bean
    public AuthenticationManager authentication(AuthenticationConfiguration authConfig)
            throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // Define la cadena de filtros de seguridad para todas las peticiones HTTP
    @Bean
    public SecurityFilterChain securityChain(HttpSecurity http, AuthenticationManager authManager)
        throws Exception{
        http.csrf(csrf -> csrf.disable()) // Desactiva CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/article/**").hasAnyRole("admin", "USER")
                        .anyRequest().authenticated()
                )
                .authenticationManager(authManager)
                .userDetailsService(userService)
                .formLogin(form -> form.permitAll())
                .httpBasic(basic -> {});
                        return http.build();
    }

}
