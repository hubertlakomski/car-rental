package pl.hubertlakomski.carrental.service.employee.edit;

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

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultEmployeeEditService implements EmployeeEditService {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void edit(EmployeeEditData data) {

        User user = userRepository.findById(
                employeeRepository.getOne(data.getId()).getUser().getId())
                .orElseThrow(EntityNotFoundException::new);

        if(data.getPassword()!=null && !data.getPassword().equals("")){
            user.setPassword(passwordEncoder.encode(data.getPassword()));
        }

        user.setActive(data.isActive());

        if (data.getRoleIdList() != null) {

            Set<String> roles = new HashSet<>();

            for (Long roleId : data.getRoleIdList()) {

                roles.add(roleRepository.getOne(roleId).getName());
            }

            user.setRoles(roles);
        }

        Employee employee = employeeRepository.findByUserId(user.getId());

        employee.setDepartment(departmentRepository.getById(data.getDepartmentId()));
        employee.setFirstName(data.getFirstName());
        employee.setLastName(data.getLastName());
        employee.setUser(user);

        employeeRepository.save(employee);
    }


}
