package pl.hubertlakomski.carrental.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hubertlakomski.carrental.domain.model.users.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
