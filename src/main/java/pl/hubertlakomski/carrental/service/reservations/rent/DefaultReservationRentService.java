package pl.hubertlakomski.carrental.service.reservations.rent;

import org.springframework.stereotype.Service;
import pl.hubertlakomski.carrental.domain.model.car.Car;
import pl.hubertlakomski.carrental.domain.model.car.Status;
import pl.hubertlakomski.carrental.domain.model.rent_process.Reservation;
import pl.hubertlakomski.carrental.domain.model.rent_process.ReservationRent;
import pl.hubertlakomski.carrental.domain.repository.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultReservationRentService implements ReservationRentService {

    private final ReservationRentRepository reservationRentRepository;
    private final ReservationRepository reservationRepository;
    private final EmployeeRepository employeeRepository;
    private final CarRepository carRepository;
    private final StatusRepository statusRepository;

    public DefaultReservationRentService(ReservationRentRepository reservationRentRepository,
                                         ReservationRepository reservationRepository,
                                         EmployeeRepository employeeRepository,
                                         CarRepository carRepository, StatusRepository statusRepository) {
        this.reservationRentRepository = reservationRentRepository;
        this.reservationRepository = reservationRepository;
        this.employeeRepository = employeeRepository;
        this.carRepository = carRepository;
        this.statusRepository = statusRepository;
    }

    @Transactional
    @Override
    public ReservationRentData prepareData(Long reservationId) {

        Reservation reservation = reservationRepository.getOne(reservationId);

        if(reservation.getRentData()!=null){
            throw new IllegalStateException("The reservation has already been rented");
        }

        ReservationRentData reservationRentData = new ReservationRentData();

        reservationRentData.setReservationId(reservation.getId());

        return reservationRentData;
    }


    @Transactional
    @Override
    public void processData(ReservationRentData reservationRentData) {

        ReservationRent reservationRent = new ReservationRent();

            reservationRent.setRealRentDate(LocalDateTime.parse(reservationRentData.getRentDate()));

            reservationRent.setComment(reservationRentData.getComment());

            Car rentedCar = carRepository.getOne(reservationRentData.getRentedCarId());
            reservationRent.setCar(rentedCar);

            Status status = statusRepository.getOne(rentedCar.getStatus().getId());

            status.setAvailable(false);
            status.setRented(true);

    //        reservationRent.setEmployee(employeeRepository
    //                .getOne(reservationRentData.getEmployeeId())); //get from security

        reservationRentRepository.save(reservationRent);

        Reservation reservation =
                reservationRepository.
                        getOne(reservationRentData.getReservationId());

        reservation.setRentData(reservationRent); //save reservation_rent_id to main reservation

    }


    public List<ReservationRentCarData> getAvailableCarsInDepartment(Long reservationId) {

        Long departmentId =
                reservationRepository.
                        getReservationById(reservationId)
                        .getRentDepartment().getId();

        List<Car> availableInDepartment =
                carRepository.findAllByDepartmentIdAndStatus_AvailableIsTrue(departmentId);

        List<ReservationRentCarData> availableInDepartmentData = new ArrayList<>();

        for(Car car: availableInDepartment){

            if(car.getStatus().isAvailable()){
                ReservationRentCarData data = new ReservationRentCarData();

                data.setBrand(car.getBrand());
                data.setId(car.getId());
                data.setModel(car.getModel());
                data.setPlateNumber(car.getPlateNumber());
                data.setSippCode(car.getSippCode().getCode());

                availableInDepartmentData.add(data);
            }

        }
        return availableInDepartmentData;
    }


}
