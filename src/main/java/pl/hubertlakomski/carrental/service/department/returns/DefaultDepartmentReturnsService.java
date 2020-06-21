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

    public DefaultDepartmentReturnsService(ReservationRepository reservationRepository, DepartmentRepository departmentRepository, ReservationRentRepository reservationRentRepository) {
        this.reservationRepository = reservationRepository;
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    @Override
    public List<DepartmentReturnsData> returnsInDepartment(Long departmentId) {

        List<DepartmentReturnsData> data = new ArrayList<>();

        List<Reservation> departmentReturns =
                reservationRepository.
                        findAllByPlannedReturnDepartmentIdOrderByPlannedReturnDate(departmentId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        for(Reservation reservation: departmentReturns){

            DepartmentReturnsData departmentReturnsData = new DepartmentReturnsData();

            departmentReturnsData.setReservationId(reservation.getId());

            Client client = reservation.getClient();

            departmentReturnsData.setClientId(client.getId());
            departmentReturnsData.setClientFullName(
                    client.getFirstName()
                    +" "+
                    client.getLastName());


            ReservationRent reservationRent = reservation.getRentData();

            if(reservationRent!=null){
                Car car = reservationRent.getCar();

                departmentReturnsData.setCarDescription(
                        car.getBrand()
                                +" "+
                                car.getModel());

                departmentReturnsData.setCarId(car.getId());

                departmentReturnsData.setRealRentDate(reservationRent.getRealRentDate().format(formatter));

                departmentReturnsData.setRentComment(reservationRent.getComment());
            }


            departmentReturnsData.setPlannedReturnDate(reservation.getPlannedReturnDate().format(formatter));

            departmentReturnsData.setSippCode(reservation.getSippCode().getCode());

            data.add(departmentReturnsData);
        }

        return data;
    }

    public String getDepartmentCode(Long id){

        return departmentRepository.getById(id).getCode();
    }
}
