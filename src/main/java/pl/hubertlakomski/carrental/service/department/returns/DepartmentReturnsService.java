package pl.hubertlakomski.carrental.service.department.returns;

import java.util.List;

public interface DepartmentReturnsService {

    List<DepartmentReturnsData> returnsInDepartment(Long departmentId);

    String getDepartmentCode(Long id);
}
