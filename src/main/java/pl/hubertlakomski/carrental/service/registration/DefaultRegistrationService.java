package pl.hubertlakomski.carrental.service.registration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.hubertlakomski.carrental.domain.model.users.User;
import pl.hubertlakomski.carrental.domain.model.users.Role;
import pl.hubertlakomski.carrental.domain.repository.RoleRepository;
import pl.hubertlakomski.carrental.domain.repository.UserRepository;

import javax.transaction.Transactional;

@Service @Slf4j
@RequiredArgsConstructor
public class DefaultRegistrationService implements RegistrationService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Transactional
    @Override
    public void register(RegistrationData registrationData) {
        log.debug("Dane do rejestracji: {}", registrationData);

        User user = new User();
        user.setUsername(registrationData.getUsername());
        user.setEmail(registrationData.getEmail());
        user.setPassword(passwordEncoder.encode(registrationData.getPassword()));
        user.setActive(true);

        Role roleUser = roleRepository.getByName("ROLE_USER");
        user.getRoles().add(roleUser);

        log.debug("Użytkownik do zapisania: {}", user);

        userRepository.save(user);

        log.debug("Zapisany użytkownik: {}", user);
    }

}
