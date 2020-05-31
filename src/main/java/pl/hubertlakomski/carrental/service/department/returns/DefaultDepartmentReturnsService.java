package pl.hubertlakomski.carrental.service.department.returns;

import org.springframework.stereotype.Service;
import pl.hubertlakomski.carrental.domain.model.Client;
import pl.hubertlakomski.carrental.domain.model.car.Car;
import pl.hubertlakomski.carrental.domain.model.rent_process.Reservation;
import pl.hubertlakomski.carrental.domain.model.rent_process.ReservationRent;
import pl.hubertlakomski.carrental.domain.repository.*;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultDepartmentReturnsService implements DepartmentReturnsService {


    private final ReservationRepository reservationRepository;
    private final DepartmentRepository departmentRepository;
    private final ReservationRentRepository reservationRentRepository;
    private final ClientRepository clientRepository;
    private final CarRepository carRepository;

    public DefaultDepartmentReturnsService(ReservationRepository reservationRepository, DepartmentRepository departmentRepository, ReservationRentRepository reservationRentRepository, ClientRepository clientRepository, CarRepository carRepository) {
        this.reservationRepository = reservationRepository;
        this.departmentRepository = departmentRepository;
        this.reservationRentRepository = reservationRentRepository;
        this.clientRepository = clientRepository;
        this.carRepository = carRepository;
    }

    @Transactional
    @Override
    public List<DepartmentReturnsData> returnsInDepartment(Long departmentId) {

        List<DepartmentReturnsData> data = new ArrayList<>();

        List<Reservation> departmentReturns =
                reservationRepository.
                        findAllByPlannedReturnDepartment(departmentRepository.getOne(departmentId));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        for(Reservation reservation: departmentReturns){

            DepartmentReturnsData departmentReturnsData = new DepartmentReturnsData();

            departmentReturnsData.setReservationId(reservation.getId());

            Client client =
                    clientRepository.getOne(reservation.getClient().getId());

            departmentReturnsData.setClientId(client.getId());
            departmentReturnsData.setClientFullName(
                    client.getFirstName()
                    +" "+
                    client.getLastName());

            ReservationRent reservationRent =
                    reservationRentRepository.getOne(reservation.getRentData().getId());

            Car car =
                    carRepository.getOne(reservationRent.getCar().getId());

            departmentReturnsData.setCarDescription(
                    car.getBrand()
                    +" "+
                    car.getModel());

            departmentReturnsData.setCarId(car.getId());

            departmentReturnsData.setSippCode(reservation.getSippCode().getCode());

            departmentReturnsData.setRealRentDate(reservationRent.getRealRentDate().format(formatter));

            departmentReturnsData.setRentComment(reservationRent.getComment());

            data.add(departmentReturnsData);
        }

        return data;
    }

    public String getDepartmentCode(Long id){

        return departmentRepository.getById(id).getCode();
    }
}
