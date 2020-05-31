package pl.hubertlakomski.carrental.service.department.reservations;

import lombok.Data;
import lombok.ToString;

@Data @ToString
public class DepartmentReservationsData {

    private String plannedRentDate;
    private String plannedReturnDate;

    private Long reservationId;

    private Long clientId;
    private String clientFullName;

    private String returnDepartmentCode;
    private Long returnDepartmentId;

    private String sippCode;

    private Long deposit;
    private Long plannedCharge;
}
