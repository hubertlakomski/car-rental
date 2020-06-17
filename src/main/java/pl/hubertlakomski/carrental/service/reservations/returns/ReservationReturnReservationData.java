package pl.hubertlakomski.carrental.service.reservations.returns;

import lombok.Data;
import lombok.ToString;
import pl.hubertlakomski.carrental.service.reservations.add.data.DepartmentData;

import java.util.List;

@Data @ToString
public class ReservationReturnReservationData {

    private String plannedReturnDate;
    private Long plannedRentalFee;
    private Long deposit;
    private String rentComment;
    private String reservationComment;
    private Long lastMileage;
    private String plannedReturnDepartment;

}
