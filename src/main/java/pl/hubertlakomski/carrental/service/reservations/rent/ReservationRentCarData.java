package pl.hubertlakomski.carrental.service.reservations.rent;

import lombok.Data;
import lombok.ToString;

@Data @ToString
public class ReservationRentCarData {

    private Long id;
    private String brand;
    private String model;
    private String plateNumber;

}
