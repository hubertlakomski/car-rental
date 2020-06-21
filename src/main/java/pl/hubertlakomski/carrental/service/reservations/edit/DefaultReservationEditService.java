package pl.hubertlakomski.carrental.service.reservations.edit;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.hubertlakomski.carrental.domain.model.rent_process.Reservation;
import pl.hubertlakomski.carrental.domain.repository.ClientRepository;
import pl.hubertlakomski.carrental.domain.repository.DepartmentRepository;
import pl.hubertlakomski.carrental.domain.repository.ReservationRepository;
import pl.hubertlakomski.carrental.domain.repository.SippCodeRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DefaultReservationEditService implements ReservationEditService {

    private final ClientRepository clientRepository;
    private final DepartmentRepository departmentRepository;
    private final SippCodeRepository sippCodeRepository;
    private final ReservationRepository reservationRepository;

    @Transactional
    @Override
    public void processEditReservation(ReservationEditData reservationEditData) {

        Reservation reservation = reservationRepository.getOne(reservationEditData.getId());

        reservation.setClient(clientRepository.getOne(reservationEditData.getClientId()));
        reservation.setPlannedRentDate(LocalDateTime.parse(reservationEditData.getPlannedRentDate()));
        reservation.setPlannedReturnDate(LocalDateTime.parse(reservationEditData.getPlannedReturnDate()));
        reservation.setRentDepartment(departmentRepository.getById(reservationEditData.getRentDepartmentId()));
        reservation.setPlannedReturnDepartment(departmentRepository.getById(reservationEditData.getPlannedReturnDepartmentId()));
        reservation.setSippCode(sippCodeRepository.getOne(reservationEditData.getSippCodeId()));
        reservation.setComment(reservationEditData.getComment());
        reservation.setPlannedRentalFee(reservationEditData.getPlannedRentalFee());
        reservation.setDeposit(reservationEditData.getDeposit());

        reservation.setPlannedRentalFee((long) reservation.getPlannedRentalFee());

        reservationRepository.save(reservation);
    }


}
