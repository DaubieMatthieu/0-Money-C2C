package isep.endoftrackproject._0money_c2c.security;

import isep.endoftrackproject._0money_c2c.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    UserService userDetailsService;
    private final String[] publicUrls = new String[]{"/", "/q", "/user", "/item/{\\d+}", "/css/**", "/js/**", "/images/**"};
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Setup the login and logout urls, as well the urls accessible anonymously
        http
                .csrf()
                .disable()
                .authenticationProvider(new AppAuthProvider(userDetailsService, passwordEncoder))
                .formLogin()
                .loginProcessingUrl("/login")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
                .authorizeRequests()
                //for now any url is allowed for all
                .antMatchers("/**")
                .permitAll()
                //all the others (so none for now) are blocked for anonymous users
                .anyRequest()
                .authenticated();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        //TODO change password encoder
        return NoOpPasswordEncoder.getInstance();
    }
}
