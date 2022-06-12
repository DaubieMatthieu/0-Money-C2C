package isep.endoftrackproject._0money_c2c.services;

import isep.endoftrackproject._0money_c2c.model.User;
import isep.endoftrackproject._0money_c2c.model.UserDTO;
import isep.endoftrackproject._0money_c2c.repositories.UserRepository;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper = new ModelMapper();

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) throw new UsernameNotFoundException("No user found with username: " + email);
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), true, true,
                true, true, new ArrayList<>());
    }

    public boolean isCurrentUser(UserDTO userDTO) {
        return isCurrentUser(userDTO.getEmail());
    }

    public boolean isCurrentUser(User user) {
        return isCurrentUser(user.getEmail());
    }

    private boolean isCurrentUser(String userEmail) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String authEmail = authentication.getName();
            return (authEmail.equals(userEmail));
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

    public UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User convertToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public User save(UserDTO userDTO) {
        return save(convertToUser(userDTO));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User register(UserDTO userDTO) {
        return register(convertToUser(userDTO));
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
