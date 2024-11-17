package br.com.fiap.global_solution.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // desativando uma configuração padrão de proteção contra ataques CSRF
                .csrf(csrf -> csrf.disable())

                // não armazena sessão do usuário
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        // Libera os métodos GET e POST para /auth/login e /auth/register
                        .requestMatchers(HttpMethod.GET, "/auth/login", "/auth/register", "/auth/register-view").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login", "/auth/register", "/auth/register-view").permitAll()

                        .requestMatchers(HttpMethod.POST, "/usuarios").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/aparelho").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/aparelho/{id}").hasRole("USER")

                        // Permitir acesso a todos para o método GET de /springai/generate
                        .requestMatchers(HttpMethod.GET, "/springai/generate").permitAll()

                        // Acesso restrito a todos os outros endpoints para usuários com role ADMIN
                        .requestMatchers("/**").hasRole("ADMIN")

                        // Exige que todas as outras requisições sejam autenticadas
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
