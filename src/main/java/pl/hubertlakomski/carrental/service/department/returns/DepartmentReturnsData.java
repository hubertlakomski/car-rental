package pl.hubertlakomski.carrental.service.department.returns;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class DepartmentReturnsData {

    @NotNull
    private Long reservationId;

    @NotNull
    private String realRentDate;
    private String rentComment;

    @NotNull
    private String plannedReturnDate;

    @NotNull
    private Long carId;

    @NotNull
    private String carDescription;

    @NotNull
    private String sippCode;

    @NotNull
    private Long clientId;

    @NotNull
    private String clientFullName;
}
