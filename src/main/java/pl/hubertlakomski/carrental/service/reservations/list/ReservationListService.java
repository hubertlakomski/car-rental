package pl.hubertlakomski.carrental.service.reservations.list;

import pl.hubertlakomski.carrental.service.reservations.data.ClientData;
import pl.hubertlakomski.carrental.service.reservations.data.DepartmentData;
import pl.hubertlakomski.carrental.service.reservations.data.SippCodeData;

import java.util.List;

public interface ReservationListService {

    List<ReservationListData> getReservations();
    List<DepartmentData> getDepartments();
    List<SippCodeData> getSippCodes();
    List<ClientData> getClients();

}
