package pl.lechowicz.queansserver.user.service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import pl.lechowicz.queansserver.common.exception.InputIncorrectException;
import pl.lechowicz.queansserver.common.validation.InputValidator;
import pl.lechowicz.queansserver.common.exception.ResourceNotFoundException;
import pl.lechowicz.queansserver.user.entity.Authorities;
import pl.lechowicz.queansserver.user.entity.AuthorityType;
import pl.lechowicz.queansserver.user.entity.User;
import pl.lechowicz.queansserver.user.modelDTO.NewUserDTO;
import pl.lechowicz.queansserver.user.modelDTO.UserDTO;
import pl.lechowicz.queansserver.user.repository.AuthoritiesRepository;
import pl.lechowicz.queansserver.user.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {
    private final InputValidator inputValidator;
    private final UserRepository userRepository;
    private final AuthoritiesRepository authoritiesRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       InputValidator inputValidator,
                       AuthoritiesRepository authoritiesRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.inputValidator = inputValidator;
        this.authoritiesRepository = authoritiesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void isValidNewUser(NewUserDTO userDTO) throws InputIncorrectException {
        inputValidator.isNotNull(userDTO);
        inputValidator.checkName(userDTO.name());
        inputValidator.checkEmail(userDTO.email());
        inputValidator.checkPassword(userDTO.password());
    }

    public boolean isUserInDB(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.isPresent();
    }

    public UserDTO createUser(NewUserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setAuthorities(Collections.singleton(getAuthorities(AuthorityType.ROLE_USER)));
        user.setEnabled(1);
        user.setHashPassword(this.passwordEncoder.encode(userDTO.password()));
        userRepository.save(user);
        return new UserDTO(user.getName(), user.getEmail());
    }

    private Authorities getAuthorities(AuthorityType authorityType){
        Optional<Authorities> possibleAuthorities = authoritiesRepository.findByName(authorityType);
        if (possibleAuthorities.isPresent()){
            return possibleAuthorities.get();
        } else{
            throw new ResourceNotFoundException(String.format("There is no %s authority", authorityType.name()));
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        if (!this.authoritiesRepository.findAll().isEmpty()) {
            return;
        }
        var entry1 = new Authorities(AuthorityType.ROLE_ADMIN);
        var entry2 = new Authorities(AuthorityType.ROLE_USER);

        authoritiesRepository.save(entry1);
        authoritiesRepository.save(entry2);
    }
}
