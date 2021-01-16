package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final SecurityProblemHandler securityProblemHandler;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> securityConfigurerAdapter;
    private final CorsConfigurationSource corsConfigurationSource;
    private static final String[] WHITE_LIST = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/swagger",
            "/actuator/**",
            "/management/**"
    };

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.addFilterBefore(new CorsFilter(corsConfigurationSource), ChannelProcessingFilter.class);
        httpSecurity
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(securityProblemHandler)
                .accessDeniedHandler(securityProblemHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers(HttpMethod.POST, "/user/create").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v*/authentication/**").permitAll()
                .antMatchers(WHITE_LIST).permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutUrl("/api/v1/authentication/logout")
                .logoutSuccessUrl("/")
                .and()
                .apply(securityConfigurerAdapter);
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
