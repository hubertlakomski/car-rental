package pl.hubertlakomski.carrental.service.car.add;

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
public class DefaultCarAddService implements  CarAddService {

    private final CarRepository carRepository;
    private final DepartmentRepository departmentRepository;
    private final SippCodeRepository sippCodeRepository;
    private final StatusRepository statusRepository;

    @Override
    public void addCar(CarAddData carAddData) {

        Department department = departmentRepository.getById(carAddData.getDepartmentId());
        SippCode sippCode = sippCodeRepository.getOne(carAddData.getSippCodeId());
        log.info(String.valueOf(carAddData.getStatusId()));
        Status status = statusRepository.getOne(carAddData.getStatusId());

        Car car = new Car();

        car.setPlateNumber(carAddData.getPlateNumber());
        car.setBrand(carAddData.getBrand());
        car.setModel(carAddData.getModel());
        car.setColor(carAddData.getColor());
        car.setProduction(carAddData.getProduction());
        car.setMileage(carAddData.getMileage());
        car.setDepartment(department);
        car.setStatus(status);
        car.setSippCode(sippCode);

        carRepository.save(car);

    }
}
