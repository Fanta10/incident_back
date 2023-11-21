package ci.gs2e.Gestion_Incidents.Config;

import ci.gs2e.Gestion_Incidents.Filter.JwtAuthFilter;
import ci.gs2e.Gestion_Incidents.Service.UserInfoService;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig implements WebMvcConfigurer {

    @Autowired
    private JwtAuthFilter authFilter;

    // User Creation
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserInfoService();
    }

    // Configuring HttpSecurity
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return
                http.csrf().disable()
                       

                        .authorizeHttpRequests()
                        .requestMatchers("/auth/welcome", "/v1/rex/**","/auth/addNewUser", "/auth/generateToken","/v1/logiciel/**","/v1/incident/**","/v1/environnement/**").permitAll()
                       // .anyRequest().authenticated()
                        .and()
                        .authorizeHttpRequests().requestMatchers("/v1/environnement/**").authenticated()



                        /*

                        .authorizeHttpRequests().requestMatchers("/auth/user/**").authenticated()
                        .and()



                        .authorizeHttpRequests().requestMatchers("/v1/incident/**").authenticated()
                        .and()
                        .authorizeHttpRequests().requestMatchers("/v1/logiciel/**").authenticated()
                        .and()
                        .authorizeHttpRequests().requestMatchers("/v1/rex/**").authenticated()
                        .and()
                        .authorizeHttpRequests().requestMatchers("/v1/environnement/**").authenticated()
                        .and()

                         */
                        .and()
                        .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and()
                        .authenticationProvider(authenticationProvider())
                        .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                        .build();



    }

    // Password Encoding
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



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200") // Angular development server
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");

    }



}