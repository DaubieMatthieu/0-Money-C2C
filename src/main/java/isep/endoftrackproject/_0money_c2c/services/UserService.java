package isep.endoftrackproject._0money_c2c.services;

import isep.endoftrackproject._0money_c2c.model.User;
import isep.endoftrackproject._0money_c2c.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
}
