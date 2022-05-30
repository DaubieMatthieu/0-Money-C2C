package isep.endoftrackproject._0money_c2c.services;

import isep.endoftrackproject._0money_c2c.model.User;
import isep.endoftrackproject._0money_c2c.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.InvalidParameterException;
import java.util.ArrayList;

@Service
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) throw new UsernameNotFoundException("No user found with username: " + email);
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), true, true,
                true, true, new ArrayList<>());
    }

    public boolean isCurrentUser(User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String email = authentication.getName();
            return (email.equals(user.getEmail()));
        }
        return false;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String email = authentication.getName();
            return userRepository.findByEmail(email);
        }
        return null;
    }

    public User register(User user) {
        checkEmailUnique(user.getEmail());
        checkUsernameUnique(user.getUsername());
        //TODO handle password encryption
        return userRepository.save(user);
    }

    private void checkEmailUnique(String email) {
        if (userRepository.findByEmail(email) != null)
            throw new InvalidParameterException("This email address is already used: " + email);
    }

    private void checkUsernameUnique(String username) {
        if (userRepository.findByUsername(username) != null)
            throw new InvalidParameterException("This username is already used: " + username);
    }
}
