package pl.hubertlakomski.carrental.service.reservations.rent;

import org.springframework.stereotype.Service;
import pl.hubertlakomski.carrental.domain.model.car.Car;
import pl.hubertlakomski.carrental.domain.model.rent_process.ReservationRent;
import pl.hubertlakomski.carrental.domain.repository.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultReservationRentService implements ReservationRentService {

    private final ReservationRentRepository reservationRentRepository;
    private final ReservationRepository reservationRepository;
    private final EmployeeRepository employeeRepository;
    private final CarRepository carRepository;
    private final DepartmentRepository departmentRepository;

    public DefaultReservationRentService(ReservationRentRepository reservationRentRepository, ReservationRepository reservationRepository, EmployeeRepository employeeRepository, CarRepository carRepository, DepartmentRepository departmentRepository) {
        this.reservationRentRepository = reservationRentRepository;
        this.reservationRepository = reservationRepository;
        this.employeeRepository = employeeRepository;
        this.carRepository = carRepository;
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    @Override
    public ReservationRentData prepareData(Long reservationId) {

        ReservationRentData reservationRentData = new ReservationRentData();

        List<ReservationRentCarData> availableInDepartmentData = getReservationRentCarData(reservationId);

        reservationRentData.setReservationId(reservationId);
        reservationRentData.setAvailableCarsInDepartment(availableInDepartmentData);

        return reservationRentData;
    }


    @Transactional
    @Override
    public void rentCar(ReservationRentData reservationRentData) {

        ReservationRent reservationRent = new ReservationRent();

        reservationRent.setRentDate(reservationRentData.getRentDate());
        reservationRent.setComment(reservationRentData.getComment());

        reservationRent.setEmployee(employeeRepository
                .getOne(reservationRentData.getEmployeeId()));
        reservationRent.setReservation(reservationRepository
                .getOne(reservationRentData.getReservationId()));

        reservationRentRepository.save(reservationRent);
    }


    private List<ReservationRentCarData> getReservationRentCarData(Long reservationId) {

        Long departmentId =
                reservationRepository.
                        getOne(reservationId)
                        .getRentDepartment().getId();

        List<Car> availableInDepartment =
                carRepository.findAllByDepartmentId(departmentId);

        List<ReservationRentCarData> availableInDepartmentData = new ArrayList<>();

        for(Car car: availableInDepartment){

            if(car.getStatus().isAvailable()){
                ReservationRentCarData data = new ReservationRentCarData();

                data.setBrand(car.getBrand());
                data.setId(car.getId());
                data.setModel(car.getModel());
                data.setPlateNumber(car.getPlateNumber());

                availableInDepartmentData.add(data);
            }

        }
        return availableInDepartmentData;
    }


}
