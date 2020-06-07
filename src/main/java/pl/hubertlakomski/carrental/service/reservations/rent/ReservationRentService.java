package pl.hubertlakomski.carrental.service.reservations.rent;


import java.util.List;

public interface ReservationRentService {

    ReservationRentData prepareData(Long reservationId);

    void processData(ReservationRentData reservationRentData);

    List<ReservationRentCarData> getAvailableCarsInDepartment(Long reservationId);
}
