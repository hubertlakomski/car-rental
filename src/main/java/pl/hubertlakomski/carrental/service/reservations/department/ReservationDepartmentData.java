package pl.hubertlakomski.carrental.service.reservations.department;

import lombok.Data;
import lombok.ToString;

@Data @ToString
public class ReservationDepartmentData {

    private String rentDepartmentCode;

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
