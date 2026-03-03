package com.codewithdevani.store.config;

import com.codewithdevani.store.services.CustomuserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        http.authorizeHttpRequests(authz ->
               authz

                       .requestMatchers(HttpMethod.POST,"/api/users").permitAll()
                       .requestMatchers("/api/users/**").authenticated()
                       .anyRequest().permitAll()

        )
                .csrf(csrf->csrf.disable())
                .formLogin(form->form.permitAll().defaultSuccessUrl("/dashboard"))
        ;

        return http.build();
    }
//     Creating in memory users (for checking prepose without database)
//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
//        UserDetails user = User.withUsername("Thiya")
//                .password(passwordEncoder.encode("user123"))
//                .roles("USER")
//                .build();
//        UserDetails admin = User.withUsername("Selva")
//                .password(passwordEncoder.encode("admin123"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(user,admin);
//
//    }
    @Bean
    public UserDetailsService userDetailsService(){

        return new CustomuserDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider authProvider =
                new DaoAuthenticationProvider(userDetailsService());

        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

    }
}
