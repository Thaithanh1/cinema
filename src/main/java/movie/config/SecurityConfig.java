package movie.config;

import lombok.RequiredArgsConstructor;
import movie.enums.RoleEnums;
import movie.util.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/product/banana", "/authentication/**", "/vnpay_return").permitAll()
                        .requestMatchers("/api/product/apple", "/api/movies/**", "/api/cinema/**", "/api/room/**", "/api/seat/**", "/api/food/**", "/api/order/**", "/authentication/change-password", "/schedules/**", "/authentication/user/**").hasAnyAuthority(RoleEnums.ADMIN.name())
                        .requestMatchers("/api/product/fish", "/authentication/change-password", "/schedules/**").hasAnyAuthority(RoleEnums.MANAGER.name())
                        .requestMatchers("/api/product/sion", "/pay", "/api/order/**", "/authentication/change-password").hasAnyAuthority(RoleEnums.USER.name())
                        .anyRequest()
                        .authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }

}
