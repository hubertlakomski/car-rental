package pl.hubertlakomski.carrental.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hubertlakomski.carrental.domain.model.car.Car;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findAllByDepartmentIdAndStatusNameEquals(Long departmentId, String statusName);

}
