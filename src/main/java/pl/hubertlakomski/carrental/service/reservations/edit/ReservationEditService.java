package pl.hubertlakomski.carrental.service.reservations.edit;

import pl.hubertlakomski.carrental.service.reservations.data.ClientData;
import pl.hubertlakomski.carrental.service.reservations.data.DepartmentData;
import pl.hubertlakomski.carrental.service.reservations.data.SippCodeData;

import java.util.List;

public interface ReservationEditService {

    void processEditReservation(ReservationEditData reservationEditData);
}
