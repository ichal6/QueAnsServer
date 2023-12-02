package pl.lechowicz.queansserver.config;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.lechowicz.queansserver.config.model.UserDetailsDTO;
import pl.lechowicz.queansserver.user.repository.UserRepository;

import static java.lang.String.format;

@Service
public class MongoUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MongoUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetailsDTO loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(username)
                .map(UserDetailsDTO::new)
                .orElseThrow(() -> new UsernameNotFoundException(format("Not found user for %s username", username)));
    }


}
