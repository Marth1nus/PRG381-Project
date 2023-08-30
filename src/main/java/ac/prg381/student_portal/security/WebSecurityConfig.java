package ac.prg381.student_portal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import ac.prg381.student_portal.services.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
  private final UserDetailsServiceImpl userDetailsServiceImpl;

  public WebSecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl) {
    this.userDetailsServiceImpl = userDetailsServiceImpl;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .authorizeHttpRequests(requests -> requests
            .requestMatchers("/", "/home").permitAll()
            .anyRequest().authenticated())

        .formLogin(form -> form
            .loginPage("/login")
            .permitAll())

        .logout(logout -> logout
            .permitAll())

        .build();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return userDetailsServiceImpl;
  }
}
