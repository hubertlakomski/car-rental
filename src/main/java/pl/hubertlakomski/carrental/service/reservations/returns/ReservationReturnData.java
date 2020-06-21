package pl.hubertlakomski.carrental.service.reservations.returns;

import lombok.Data;
import lombok.ToString;
import pl.hubertlakomski.carrental.service.reservations.data.DepartmentData;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class ReservationReturnData {

    @NotNull
    private Long reservationId;

    @NotNull
    private Long realRentalFee;
    @NotNull
    private Long depositCharge;
    private String comment;

    @NotNull
    private Long realReturnDepartmentId;

    @NotNull
    private Long mileage;

}
