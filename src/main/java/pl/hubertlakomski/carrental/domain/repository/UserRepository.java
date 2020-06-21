package pl.hubertlakomski.carrental.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.hubertlakomski.carrental.domain.model.users.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.id = ?1")
    User findUserByIdJoinRoles(Long userId);

    Optional<User> findById(Long id);
}
