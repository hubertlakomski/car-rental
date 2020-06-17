package pl.hubertlakomski.carrental.service.car.edit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.hubertlakomski.carrental.domain.model.Department;
import pl.hubertlakomski.carrental.domain.model.car.Car;
import pl.hubertlakomski.carrental.domain.model.car.SippCode;
import pl.hubertlakomski.carrental.domain.model.car.Status;
import pl.hubertlakomski.carrental.domain.repository.CarRepository;
import pl.hubertlakomski.carrental.domain.repository.DepartmentRepository;
import pl.hubertlakomski.carrental.domain.repository.SippCodeRepository;
import pl.hubertlakomski.carrental.domain.repository.StatusRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class DefaultCarEditService implements CarEditService {

    private final CarRepository carRepository;
    private final DepartmentRepository departmentRepository;
    private final SippCodeRepository sippCodeRepository;
    private final StatusRepository statusRepository;

    @Override
    public void editCar(CarEditData carEditData) {

        Department department = departmentRepository.getById(carEditData.getDepartmentId());
        SippCode sippCode = sippCodeRepository.getOne(carEditData.getSippCodeId());
        Status status = statusRepository.getOne(carEditData.getStatusId());

        Car car = new Car();

        car.setId(carEditData.getId());
        car.setPlateNumber(carEditData.getPlateNumber());
        car.setBrand(carEditData.getBrand());
        car.setModel(carEditData.getModel());
        car.setColor(carEditData.getColor());
        car.setProduction(carEditData.getProduction());
        car.setMileage(carEditData.getMileage());
        car.setDepartment(department);
        car.setStatus(status);
        car.setSippCode(sippCode);

        carRepository.save(car);

    }
}
