package ru.gpn.common.starters.security.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
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
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import ru.gpn.common.starters.security.configuration.properties.CorsProperties;
import ru.gpn.common.starters.security.configuration.properties.UrlProperties;
import ru.gpn.common.starters.security.configuration.properties.WhiteListProperties;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
@EnableConfigurationProperties({WhiteListProperties.class, CorsProperties.class, UrlProperties.class})
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final SecurityProblemHandler securityProblemHandler;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final WhiteListProperties whiteListProperties;
    private final SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> securityConfigurerAdapter;
    private final CorsConfigurationSource corsConfigurationSource;
    private final CorsProperties corsProperties;
    private final UrlProperties urlProperties;

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        if (corsProperties.isEnabled()) {
            httpSecurity.addFilterBefore(new CorsFilter(corsConfigurationSource), ChannelProcessingFilter.class);
        }
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
                .antMatchers(HttpMethod.POST, "/api/v*/authentication/**").permitAll()
                .antMatchers(whiteListProperties.getWhiteList()).permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutUrl(urlProperties.getLogout())
                .logoutSuccessUrl(urlProperties.getLogoutSuccess())
                .and()
                .apply(securityConfigurerAdapter);
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
