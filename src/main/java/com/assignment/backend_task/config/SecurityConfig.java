// package com.assignment.backend_task.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.Customizer;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// public class SecurityConfig {

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//         http
//             .csrf(csrf -> csrf.disable())
//             .authorizeHttpRequests(auth -> auth
//                 // ADMIN access
//                 .requestMatchers("/api/students/**").hasRole("ADMIN")
//                 .requestMatchers("/api/courses/**").hasRole("ADMIN")

//                 // STUDENT access
//                 .requestMatchers("/api/student/**").hasRole("STUDENT")

//                 .anyRequest().authenticated()
//             )
//             .httpBasic(Customizer.withDefaults()); // ✅ FIXED

//         return http.build();
//     }
// }

package com.assignment.backend_task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/students/**").hasRole("ADMIN")
                .requestMatchers("/api/student/**").hasAnyRole("ADMIN", "STUDENT")
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
