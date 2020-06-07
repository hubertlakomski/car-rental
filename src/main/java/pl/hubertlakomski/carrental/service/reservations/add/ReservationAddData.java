package pl.hubertlakomski.carrental.service.reservations.add;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class ReservationAddData {

    @NotNull
    private String plannedRentDate;
    @NotNull
    private String plannedReturnDate;

    @NotNull
    private Long rentDepartmentId;
    @NotNull
    private Long plannedReturnDepartmentId;

    @NotNull
    private Long clientId;

    @NotNull
    private Long sippCodeId;

    private String comment;
}
