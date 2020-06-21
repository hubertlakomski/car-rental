package pl.hubertlakomski.carrental.service.reservations.list;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data @ToString
public class ReservationListData {

    private Long id;
    private String plannedRentDate;
    private String plannedReturnDate;
    private String rentDepartmentCode;
    private String returnDepartmentCode;
    private String clientFullName;
    private String sippCode;
    private Long plannedRentalFee;
    private Long deposit;
    private String comment;

    private Long rentDepartmentId;
    private Long returnDepartmentId;
    private Long clientId;
    private Long sippCodeId;

    private LocalDateTime plannedRentDateLDT;
    private LocalDateTime plannedReturnDateLDT;

}
