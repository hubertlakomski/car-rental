package pl.hubertlakomski.carrental.service.reservations.edit;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ToString
public class ReservationEditData {

    private Long id;

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

    @NotNull
    private Long deposit;

    @NotNull
    private Long plannedRentalFee;

    private String comment;
}
