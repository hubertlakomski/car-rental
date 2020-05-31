package pl.hubertlakomski.carrental.service.reservations.rent;

public interface ReservationRentService {

    ReservationRentData prepareData(Long reservationId);

    void processData(ReservationRentData reservationRentData);

}
