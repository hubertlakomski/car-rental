package pl.hubertlakomski.carrental.service.reservations.department;

import org.springframework.stereotype.Service;
import pl.hubertlakomski.carrental.domain.model.rent_process.Reservation;
import pl.hubertlakomski.carrental.domain.repository.DepartmentRepository;
import pl.hubertlakomski.carrental.domain.repository.ReservationRepository;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultReservationDepartmentService implements ReservationDepartmentService {

    private final ReservationRepository reservationRepository;
    private final DepartmentRepository departmentRepository;

    public DefaultReservationDepartmentService(ReservationRepository reservationRepository, DepartmentRepository departmentRepository) {
        this.reservationRepository = reservationRepository;
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    @Override
    public List<ReservationDepartmentData> reservationsInDepartment(Long departmentId) {

        List<ReservationDepartmentData> data = new ArrayList<>();
        List<Reservation> departmentReservations =
                reservationRepository.findAllByRentDepartmentIdOrderByPlannedRentDate(departmentId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        for(Reservation reservation: departmentReservations){

            ReservationDepartmentData reservationData = new ReservationDepartmentData();

            reservationData.setPlannedRentDate(reservation.getPlannedRentDate().format(formatter));
            reservationData.setPlannedReturnDate(reservation.getPlannedReturnDate().format(formatter));

            reservationData.setReservationId(reservation.getId());

            reservationData.setClientId(reservation.getClient().getId());
            reservationData.setClientFullName(reservation.getClient().getFirstName()+" "+
                    reservation.getClient().getLastName());

            reservationData.setReturnDepartmentId(reservation.getReturnDepartment().getId());
            reservationData.setReturnDepartmentCode(reservation.getReturnDepartment().getCode());

            reservationData.setSippCode(reservation.getSippCode().getCode());

            reservationData.setDeposit(reservation.getDeposit());
            reservationData.setPlannedCharge(reservation.getPlannedCharge());

            data.add(reservationData);
        }

        return data;
    }

    public String getDepartmentCode(Long id){

        return departmentRepository.getById(id).getCode();
    }

}
