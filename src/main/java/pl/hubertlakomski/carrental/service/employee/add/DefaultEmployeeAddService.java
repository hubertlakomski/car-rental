package pl.hubertlakomski.carrental.service.employee.add;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.hubertlakomski.carrental.domain.model.users.Employee;
import pl.hubertlakomski.carrental.domain.model.users.User;
import pl.hubertlakomski.carrental.domain.repository.DepartmentRepository;
import pl.hubertlakomski.carrental.domain.repository.EmployeeRepository;
import pl.hubertlakomski.carrental.domain.repository.RoleRepository;
import pl.hubertlakomski.carrental.domain.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultEmployeeAddService implements EmployeeAddService{

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void add(EmployeeAddData data) {

        User user = new User();

        user.setUsername(data.getUsername());
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.setActive(data.isActive());

        Set<String> roles = new HashSet<>();

        for(Long roleId: data.getRoleIdList()){

            roles.add(roleRepository.getOne(roleId).getName());
        }

        user.setRoles(roles);

        userRepository.save(user);

        Employee employee = new Employee();

        employee.setDepartment(departmentRepository.getById(data.getDepartmentId()));
        employee.setFirstName(data.getFirstName());
        employee.setLastName(data.getLastName());
        employee.setUser(user);

        employeeRepository.save(employee);
    }


}
