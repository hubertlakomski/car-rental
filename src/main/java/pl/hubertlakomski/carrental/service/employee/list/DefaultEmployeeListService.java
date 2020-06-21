package pl.hubertlakomski.carrental.service.employee.list;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.hubertlakomski.carrental.domain.model.users.Employee;
import pl.hubertlakomski.carrental.domain.model.users.Role;
import pl.hubertlakomski.carrental.domain.model.users.User;
import pl.hubertlakomski.carrental.domain.repository.DepartmentRepository;
import pl.hubertlakomski.carrental.domain.repository.EmployeeRepository;
import pl.hubertlakomski.carrental.domain.repository.RoleRepository;
import pl.hubertlakomski.carrental.domain.repository.UserRepository;
import pl.hubertlakomski.carrental.service.reservations.data.DepartmentData;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static pl.hubertlakomski.carrental.service.car.list.DefaultCarListService.getDepartmentData;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultEmployeeListService implements EmployeeListService {

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public List<EmployeeListData> getEmployees() {

        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeListData> employeeListData = new ArrayList<>();

        for (Employee employee : employees) {

            EmployeeListData data = new EmployeeListData();

            data.setId(employee.getId());
            data.setFirstName(employee.getFirstName());
            data.setLastName(employee.getLastName());

            if (employee.getDepartment() != null) {
                data.setDepartmentId(employee.getDepartment().getId());
                data.setDepartmentCode(employee.getDepartment().getCode());
            }

            data.setUserId(employee.getUser().getId());
            data.setActive(employee.getUser().getActive());
            data.setUsername(employee.getUser().getUsername());
            data.setPassword(employee.getUser().getPassword());

            log.info("User id: {}", data.getUserId());
            log.info("Employee id: {}", data.getId());
            User user = userRepository.findUserByIdJoinRoles(data.getUserId());

            Set<String> roles = user.getRoles();

            data.setRoles(roles);

            employeeListData.add(data);
        }


        return employeeListData;
    }

    @Transactional
    @Override
    public List<Role> getRoles() {

        return roleRepository.findAll();
    }

    @Override
    public List<DepartmentData> getDepartments() {

        return getDepartmentData(departmentRepository);
    }
}
