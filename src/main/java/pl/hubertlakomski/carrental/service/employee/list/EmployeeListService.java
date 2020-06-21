package pl.hubertlakomski.carrental.service.employee.list;

import pl.hubertlakomski.carrental.domain.model.users.Role;
import pl.hubertlakomski.carrental.service.reservations.data.DepartmentData;

import java.util.List;

public interface EmployeeListService {

    List<EmployeeListData> getEmployees();
    List<Role> getRoles();
    List<DepartmentData> getDepartments();

}
