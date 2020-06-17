package pl.hubertlakomski.carrental.service.car.list;

import pl.hubertlakomski.carrental.service.car.list.data.StatusData;
import pl.hubertlakomski.carrental.service.reservations.add.data.DepartmentData;
import pl.hubertlakomski.carrental.service.reservations.add.data.SippCodeData;

import java.util.List;

public interface CarListService {

    List<DepartmentData> getDepartments();
    List<SippCodeData> getSippCodes();
    List<StatusData> getStatuses();

    List<CarListData> getCars();


}
