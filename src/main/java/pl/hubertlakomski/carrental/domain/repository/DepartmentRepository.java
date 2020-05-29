package pl.hubertlakomski.carrental.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hubertlakomski.carrental.domain.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department getById(Long id);
}
