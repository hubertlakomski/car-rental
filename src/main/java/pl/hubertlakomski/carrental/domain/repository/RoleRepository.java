package pl.hubertlakomski.carrental.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hubertlakomski.carrental.domain.model.users.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getByName(String role_user);
}
