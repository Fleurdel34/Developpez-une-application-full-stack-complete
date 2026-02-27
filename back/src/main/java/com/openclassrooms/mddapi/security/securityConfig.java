package com.openclassrooms.mddapi.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


import static org.springframework.http.HttpMethod.POST;

/** Create account to access api
 * security api back end
 * @request post: permit all
 * @method filter to verify identified user
 */

@Configuration
@EnableMethodSecurity
public class securityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer:: disable)
                .authorizeHttpRequests(authorize-> {
                    authorize.requestMatchers(POST, "/api/register").permitAll();
                    authorize.requestMatchers(POST, "/api/login").permitAll();
                    authorize.anyRequest().authenticated();
                })
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
}
