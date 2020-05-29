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

    @NotNull @NotBlank
    private LocalDateTime rentDate; //actual rental time

    @NotNull
    private Long reservationId;

    @NotBlank @NotNull
    private ReservationRentCarData rentedCar;

    private Long employeeId; //get from security

    @Size(max=500)
    private String comment;

    private List<ReservationRentCarData> availableCarsInDepartment
            = new ArrayList<>();
}
