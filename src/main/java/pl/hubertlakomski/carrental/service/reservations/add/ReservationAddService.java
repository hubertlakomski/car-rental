package pl.hubertlakomski.carrental.service.reservations.add;

import pl.hubertlakomski.carrental.service.reservations.add.data.ClientData;
import pl.hubertlakomski.carrental.service.reservations.add.data.DepartmentData;
import pl.hubertlakomski.carrental.service.reservations.add.data.SippCodeData;

import java.util.List;

public interface ReservationAddService {

    List<ClientData> getClients();
    List<DepartmentData> getDepartments();
    List<SippCodeData> getSippCodes();

    Long processAddReservation(ReservationAddData reservationAddData);

}
