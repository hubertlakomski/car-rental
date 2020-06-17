package pl.hubertlakomski.carrental.service.reservations.rent;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class DefaultReservationRentService implements ReservationRentService {

    private final ReservationRentRepository reservationRentRepository;
    private final ReservationRepository reservationRepository;
    private final CarRepository carRepository;
    private final StatusRepository statusRepository;

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

            reservationRent.setRealRentDate(LocalDateTime.now());

            reservationRent.setComment(reservationRentData.getComment());

            Car rentedCar = carRepository.getOne(reservationRentData.getRentedCarId());
            reservationRent.setCar(rentedCar);

            Status rentedStatus = statusRepository.findByName("rented");
            rentedCar.setStatus(rentedStatus);

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
                carRepository.findAllByDepartmentIdAndStatusNameEquals(departmentId, "available");

        List<ReservationRentCarData> availableInDepartmentData = new ArrayList<>();

        for(Car car: availableInDepartment){

            if(car.getStatus().getName().equals("available")){
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
