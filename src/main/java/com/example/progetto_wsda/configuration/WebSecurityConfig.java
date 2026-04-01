package com.example.progetto_wsda.configuration;

import com.example.progetto_wsda.service.DettagliUtenteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    UserDetailsService userDetailsService() {
        return new DettagliUtenteService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequest->
                        authorizeRequest
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/ledWall").permitAll()
                                .requestMatchers("/report").permitAll()
                                .requestMatchers("/addImpianto").permitAll()
                                .requestMatchers("/deleteImpianto").permitAll()
                                .requestMatchers("/updateImpianto").permitAll()
                                .requestMatchers("/inattivo").permitAll()
                                .requestMatchers("/css/**","/html/**","/images/**","/js/**","/xml/**").permitAll()
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/gestione").hasAnyAuthority("ADMIN")
                                .anyRequest().authenticated()
                        )
                        .formLogin(formLogin -> formLogin
                                .loginPage("/login")
                                .defaultSuccessUrl("/gestione")
                                .permitAll()
                                .failureUrl("/login?error=true"))
                        .logout((logout) -> logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/")
                                .permitAll())
                                .csrf().disable()
                                .headers().frameOptions().sameOrigin();
        ;
        return http.build();
    }



}
