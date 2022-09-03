package com.tvn.api_for_1c_v2.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
//    private DataSource dataSource;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("/remoteCodeProvider/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .rememberMe()
                    .rememberMeParameter("remember-me")
                .and()
                    .logout().permitAll();



        return http.build();
    }
//TODO  figure out how to make a configuration without "WebSecurityConfigurerAdapter"
//@Override
//protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.userDetailsService(userService)
//            .passwordEncoder(passwordEncoder);
//}
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder authConfig) throws Exception {
//        authConfig.userDetailsService(userService)
//                .passwordEncoder(NoOpPasswordEncoder.getInstance());
//        return authConfig.getAuthenticationManager();
//    }
}
