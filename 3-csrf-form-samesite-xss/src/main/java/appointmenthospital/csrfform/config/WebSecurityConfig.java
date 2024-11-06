package appointmenthospital.csrfform.config;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.DefaultCookieSerializerCustomizer;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.boot.web.server.Cookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import java.util.function.Consumer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        final CookieCsrfTokenRepository csrfTokenRepository= CookieCsrfTokenRepository.withHttpOnlyFalse();

        csrfTokenRepository.setCookieCustomizer(new Consumer<ResponseCookie.ResponseCookieBuilder>() {
            @Override
            public void accept(ResponseCookie.ResponseCookieBuilder t) {
                t.sameSite("strict");
                t.secure(true);
                t.httpOnly(false); //Js can read
            }
        });
        http
//                .csrf((csrf) -> csrf
//                        .csrfTokenRepository(new HttpSessionCsrfTokenRepository())
//                )

              //  .cors(Customizer.withDefaults())
              // .csrf(AbstractHttpConfigurer::disable)
                .csrf(csrf->csrf.csrfTokenRepository(csrfTokenRepository))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/home","/xss").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .authenticationManager(authenticationManager())
                .logout((logout) -> logout.permitAll());

        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(5);
    }
    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return new ProviderManager(authenticationProvider);

    }
}