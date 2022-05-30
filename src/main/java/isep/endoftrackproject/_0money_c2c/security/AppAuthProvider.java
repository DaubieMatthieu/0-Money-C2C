package isep.endoftrackproject._0money_c2c.security;

import isep.endoftrackproject._0money_c2c.services.UserService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AppAuthProvider extends DaoAuthenticationProvider {
    private final UserService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
        String email = auth.getName();
        String providedPassword = auth.getCredentials().toString();
        UserDetails user = userDetailsService.loadUserByUsername(email);
        //TODO handle encryption
        if (!providedPassword.equals(user.getPassword())) {
            throw new BadCredentialsException("Username/Password does not match for " + auth.getPrincipal());
        }
        return new UsernamePasswordAuthenticationToken(user, providedPassword, user.getAuthorities());
    }

    public AppAuthProvider(UserService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.setPasswordEncoder(passwordEncoder);
    }
}
