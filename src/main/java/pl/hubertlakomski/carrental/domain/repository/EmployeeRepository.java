package pl.hubertlakomski.carrental.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hubertlakomski.carrental.domain.model.users.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository <Employee, Long> {

    List<Employee> findAllByDepartmentId(Long id);

}
