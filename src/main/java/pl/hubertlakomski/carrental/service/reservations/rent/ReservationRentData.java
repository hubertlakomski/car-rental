package pl.hubertlakomski.carrental.service.reservations.rent;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data @ToString
public class ReservationRentData {

    @NotNull
    private String rentDate; //actual rental time

    @NotNull
    private Long reservationId;

    @NotNull
    private Long rentedCarId;

    private Long employeeId; //get from security

    @Size(max=500)
    private String comment;
}
