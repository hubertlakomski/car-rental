package pl.hubertlakomski.carrental.service.department.reservations;

import org.springframework.stereotype.Service;
import pl.hubertlakomski.carrental.domain.model.rent_process.Reservation;
import pl.hubertlakomski.carrental.domain.repository.DepartmentRepository;
import pl.hubertlakomski.carrental.domain.repository.ReservationRepository;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultDepartmentReservationsService implements DepartmentReservationsService {

    private final ReservationRepository reservationRepository;
    private final DepartmentRepository departmentRepository;

    public DefaultDepartmentReservationsService(ReservationRepository reservationRepository, DepartmentRepository departmentRepository) {
        this.reservationRepository = reservationRepository;
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    @Override
    public List<DepartmentReservationsData> reservationsInDepartment(Long departmentId) {

        List<DepartmentReservationsData> data = new ArrayList<>();
        List<Reservation> departmentReservations =
                reservationRepository.findAllByRentDepartmentIdAndRentDataIsNullOrderByPlannedRentDate(departmentId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        for(Reservation reservation: departmentReservations){

            DepartmentReservationsData reservationData = new DepartmentReservationsData();

            reservationData.setPlannedRentDate(reservation.getPlannedRentDate().format(formatter));
            reservationData.setPlannedReturnDate(reservation.getPlannedReturnDate().format(formatter));

            reservationData.setReservationId(reservation.getId());

            reservationData.setClientId(reservation.getClient().getId());
            reservationData.setClientFullName(reservation.getClient().getFirstName()+" "+
                    reservation.getClient().getLastName());

            reservationData.setReturnDepartmentId(reservation.getPlannedReturnDepartment().getId());
            reservationData.setReturnDepartmentCode(reservation.getPlannedReturnDepartment().getCode());

            reservationData.setSippCode(reservation.getSippCode().getCode());
            reservationData.setDeposit(reservation.getSippCode().getDeposit());

            reservationData.setPlannedCharge(reservation.getPlannedRentalFee());

            data.add(reservationData);
        }

        return data;
    }

    public String getDepartmentCode(Long id){

        return departmentRepository.getById(id).getCode();
    }

}
