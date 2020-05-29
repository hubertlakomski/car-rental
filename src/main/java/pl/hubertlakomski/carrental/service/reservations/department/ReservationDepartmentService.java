package pl.hubertlakomski.carrental.service.reservations.department;

import java.util.List;

public interface ReservationDepartmentService {

    List<ReservationDepartmentData> reservationsInDepartment(Long departmentId);

    String getDepartmentCode(Long id);
}
