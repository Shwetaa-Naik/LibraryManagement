package org.shweta.LibraryManagement.configuration;

import org.shweta.LibraryManagement.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    //Authentication
    //securityFilterChain :: Authorization
    //password encoder
    //
    @Value("${student.authority}")
    private String studentAuthority;
    @Value("${admin.authority}")
    private String adminAuthority;
    @Autowired
    private StudentService studentService;

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(studentService);
        daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(authorise ->authorise
                .requestMatchers("/transaction/create/**").hasAnyAuthority(studentAuthority,adminAuthority)
                .anyRequest().permitAll()

        ).formLogin(withDefaults()).httpBasic(withDefaults()).csrf(csrf->csrf.disable());

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){

        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }
}
