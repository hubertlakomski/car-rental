package pl.hubertlakomski.carrental.service.reservations.rent;

import org.springframework.stereotype.Service;
import pl.hubertlakomski.carrental.domain.model.car.Car;
import pl.hubertlakomski.carrental.domain.model.rent_process.Reservation;
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

    public DefaultReservationRentService(ReservationRentRepository reservationRentRepository,
                                         ReservationRepository reservationRepository,
                                         EmployeeRepository employeeRepository,
                                         CarRepository carRepository) {
        this.reservationRentRepository = reservationRentRepository;
        this.reservationRepository = reservationRepository;
        this.employeeRepository = employeeRepository;
        this.carRepository = carRepository;
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
    public void processData(ReservationRentData reservationRentData) {

        ReservationRent reservationRent = new ReservationRent();

        reservationRent.setRealRentDate(reservationRentData.getRentDate());
        reservationRent.setComment(reservationRentData.getComment());

        reservationRent.setEmployee(employeeRepository
                .getOne(reservationRentData.getEmployeeId()));

        reservationRentRepository.save(reservationRent);

        Reservation reservation =
                reservationRepository.
                        getOne(reservationRentData.getReservationId());

        reservation.setRentData(reservationRent); //save reservation_rent_id to main reservation

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
