package pl.hubertlakomski.carrental.service.car.list;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.hubertlakomski.carrental.domain.model.Department;
import pl.hubertlakomski.carrental.domain.model.car.Car;
import pl.hubertlakomski.carrental.domain.model.car.SippCode;
import pl.hubertlakomski.carrental.domain.model.car.Status;
import pl.hubertlakomski.carrental.domain.repository.CarRepository;
import pl.hubertlakomski.carrental.domain.repository.DepartmentRepository;
import pl.hubertlakomski.carrental.domain.repository.SippCodeRepository;
import pl.hubertlakomski.carrental.domain.repository.StatusRepository;
import pl.hubertlakomski.carrental.service.car.list.data.StatusData;
import pl.hubertlakomski.carrental.service.reservations.add.data.DepartmentData;
import pl.hubertlakomski.carrental.service.reservations.add.data.SippCodeData;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultCarListService implements CarListService {


    private final CarRepository carRepository;
    private final DepartmentRepository departmentRepository;
    private final SippCodeRepository sippCodeRepository;
    private final StatusRepository statusRepository;

    @Transactional
    @Override
    public List<CarListData> getCars() {

        List<CarListData> carListData = new ArrayList<>();

        for(Car car: carRepository.findAll()){

            CarListData data = new CarListData();

            data.setId(car.getId());

            data.setPlateNumber(car.getPlateNumber());
            data.setBrand(car.getBrand());
            data.setModel(car.getModel());
            data.setColor(car.getColor());
            data.setProduction(car.getProduction());
            data.setMileage(car.getMileage());

            data.setSippCodeDsc(car.getSippCode().getCode());
            data.setSippCodeId(car.getSippCode().getId());

            data.setDepartmentDsc(car.getDepartment().getCode());
            data.setDepartmentId(car.getDepartment().getId());

            data.setStatusDsc(car.getStatus().getPlDescription());
            data.setStatusId(car.getStatus().getId());

            carListData.add(data);
        }

        return carListData;
    }

    @Override
    public List<StatusData> getStatuses(){

        List<Status> statuses = statusRepository.findAll();
        List<StatusData> statusDataList = new ArrayList<>();

        for(Status status: statuses){

            StatusData statusData = new StatusData();

            statusData.setId(status.getId());
            statusData.setName(status.getName());
            statusData.setPlDescription(status.getPlDescription());

            statusDataList.add(statusData);
        }

        return statusDataList;
    }

    @Override
    public List<DepartmentData> getDepartments() {
        return getDepartmentData(departmentRepository);
    }

    public static List<DepartmentData> getDepartmentData(DepartmentRepository departmentRepository) {
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentData> departmentDataList = new ArrayList<>();

        for(Department department: departments){

            DepartmentData departmentData = new DepartmentData();

            departmentData.setAddress(department.getAddress());
            departmentData.setCode(department.getCode());
            departmentData.setId(department.getId());

            departmentDataList.add(departmentData);
        }

        return departmentDataList;
    }

    @Override
    public List<SippCodeData> getSippCodes() {
        return getSippCodeData(sippCodeRepository);
    }

    public static List<SippCodeData> getSippCodeData(SippCodeRepository sippCodeRepository) {
        List<SippCode> sippCodes = sippCodeRepository.findAll();
        List<SippCodeData> sippCodeDataList = new ArrayList<>();

        for(SippCode sippCode: sippCodes){

            SippCodeData sippCodeData = new SippCodeData();

            sippCodeData.setCode(sippCode.getCode());
            sippCodeData.setId(sippCode.getId());

            sippCodeDataList.add(sippCodeData);
        }

        return sippCodeDataList;
    }

}
