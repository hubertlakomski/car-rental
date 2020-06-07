package pl.hubertlakomski.carrental.service.reservations.returns;

public interface ReservationReturnService {
    ReservationReturnData prepareData(Long reservationId);

    ReservationReturnReservationData getReservationData(Long reservationId);

    void processData(ReservationReturnData reservationReturnData);
}
