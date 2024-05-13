package com.HuiyutangErp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {

        return new UserInfoUserDetailsService();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
        .cors(Customizer.withDefaults())
                .authorizeHttpRequests(
                        req -> {
                        	// 不攔截
//                            req.requestMatchers("/admin/**","/css/**","/images/**","/js/**","/templates/**","/static/**","/assets/**","/admin/restock/**").permitAll();
//                            req.requestMatchers("/admin/manufacterur/**","/admin/restock/**").hasAnyRole("USER");
                        	 req.anyRequest().permitAll();
//                            req.anyRequest().authenticated();
                        }
                )
                .httpBasic(Customizer.withDefaults())
                .logout(logout ->
                logout
                    .logoutUrl("/basic/basiclogout")
                    .addLogoutHandler(new SecurityContextLogoutHandler())
            );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}