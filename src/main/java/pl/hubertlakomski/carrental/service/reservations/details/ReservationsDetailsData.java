package pl.hubertlakomski.carrental.service.reservations.details;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;


@Data
@ToString
public class ReservationsDetailsData {

    @NotNull
    private Long id;

    @NotNull
    private String created;

    @NotNull
    private String client;

    @NotNull
    private String sippCode;

    @NotNull
    private String plannedRentDate;
    private String realRentDate;

    @NotNull
    private String plannedReturnDate;
    private String realReturnDate;

    @NotNull
    private String rentDepartment;

    @NotNull
    private String plannedReturnDepartment;
    private String realReturnDepartment;

    @NotNull
    private Long plannedCharge; //amount from SIPPcode perDay*reservation_time
    private Long realCharge; //calculated by user

    private Long deposit; //amount from SIPPcode deposit
    private Long depositCharge;

}
