package pl.hubertlakomski.carrental.service.reservations.details;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class ReservationDetailsData {

    private String reservationId;
    private String sippCode;
    private String rentedCar;
    private String plannedRentDate;
    private String realRentDate;
    private String plannedReturnDate;
    private String realReturnDate;
    private String rentDepartment;
    private String plannedReturnDepartment;
    private String realReturnDepartment;
    private String client;
    private String plannedRentalFee;
    private String realRentalFee;
    private String deposit;
    private String depositCharge;
    private String reservationComment;
    private String rentComment;
    private String returnComment;


}
