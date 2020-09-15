package org.dantes.edmon.config;

import org.dantes.edmon.jwt.JwtConfig;
import org.dantes.edmon.jwt.JwtTokenVerifier;
import org.dantes.edmon.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;
import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;
    private PasswordEncoder passwordEncoder;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;

    public SecurityConfig(DataSource dataSource, PasswordEncoder passwordEncoder,
                          SecretKey secretKey, JwtConfig jwtConfig){
        this.dataSource = dataSource;
        this.passwordEncoder = passwordEncoder;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select email, password, enabled from users " +
                                "where email=?")
                .authoritiesByUsernameQuery(
                        "select user_email, authority from authorities " +
                                "where user_email=?")
                .passwordEncoder(passwordEncoder);
    }

    private final String ADMIN_MANAGEMENT_ENDPOINT = "/management/**";
    private final String REVIEW_ENDPOINT = "/review/**";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
                    .addFilterAfter(new JwtTokenVerifier(secretKey, jwtConfig), JwtUsernameAndPasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers(ADMIN_MANAGEMENT_ENDPOINT)
                        .access("hasRole('ROLE_ADMIN')")
                    .antMatchers(HttpMethod.POST, REVIEW_ENDPOINT)
                        .access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                    .antMatchers(HttpMethod.DELETE, REVIEW_ENDPOINT)
                        .access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                    .antMatchers("/**").permitAll();

    }
}
