package com.proyecto_final.config;

import com.proyecto_final.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityService securityService;

    /**
     * Crea y expone un bean para el codificador de contraseñas
     * utilizando BCrypt.
     *
     * @return instancia de BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configura el proveedor de autenticación utilizando
     * el servicio personalizado de seguridad y el encoder.
     *
     * @return DaoAuthenticationProvider configurado
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(securityService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * Construye y expone el AuthenticationManager,
     * agregando el proveedor de autenticación personalizado.
     *
     * @param http instancia de HttpSecurity
     * @return AuthenticationManager configurado
     * @throws Exception en caso de error en la configuración
     */
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder auth =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        auth.authenticationProvider(authenticationProvider());

        return auth.build();
    }

    /**
     * Configura la cadena de filtros de seguridad de Spring Security,
     * definiendo reglas de autorización, login, logout y manejo de roles.
     *
     * @param http instancia de HttpSecurity
     * @return SecurityFilterChain configurado
     * @throws Exception en caso de error en la configuración
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/registro", "/css/**", "/img/**", "/").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/usuario/**").hasRole("USUARIO")
                .requestMatchers("/veterinario/**").hasRole("VETERINARIO")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler((request, response, authentication) -> {

                    String rol = authentication.getAuthorities()
                            .iterator()
                            .next()
                            .getAuthority();

                    if (rol.equals("ROLE_ADMIN")) {
                        response.sendRedirect("/admin");
                    } else if (rol.equals("ROLE_VETERINARIO")) {
                        response.sendRedirect("/veterinario");
                    } else if (rol.equals("ROLE_USUARIO")) {
                        response.sendRedirect("/usuario");
                    }
                })
                .failureUrl("/login?error")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        return http.build();
    }
}
