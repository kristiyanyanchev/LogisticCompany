package org.logistic.company.logisticcompany.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                        .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/profile").authenticated()
                                .requestMatchers("/package/getPackagesForCurrentUser").hasRole("USER")
                                .requestMatchers("/package/**").hasRole("ADMIN")
                                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                                .requestMatchers("/home/**").permitAll()
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/register").permitAll()
                                .requestMatchers("/fragments/**").permitAll())
                        .formLogin((form) -> form
                                .loginPage("/login")
                                .defaultSuccessUrl("/", true)
                                .permitAll())
                        .logout((logout) -> logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/")
                                .permitAll())
                        .csrf(csrf -> csrf
                                .ignoringRequestMatchers("/logout"));

                return http.build();
        }

        @Bean
        public UserDetailsService userDetailsService(DataSource dataSource) {
                UserDetails userDetails = User.builder()
                                .username("user")
                                .password("{noop}password")
                                .roles("USER")
                                .build();

                return new JdbcUserDetailsManager(dataSource);
        }

}
