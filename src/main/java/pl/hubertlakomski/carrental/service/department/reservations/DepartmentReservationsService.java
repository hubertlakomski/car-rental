package pl.hubertlakomski.carrental.service.department.reservations;

import java.util.List;

public interface DepartmentReservationsService {

    List<DepartmentReservationsData> reservationsInDepartment(Long departmentId);

    String getDepartmentCode(Long id);
}
