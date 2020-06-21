package pl.hubertlakomski.carrental.service.reservations.returns;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.hubertlakomski.carrental.domain.model.car.Status;
import pl.hubertlakomski.carrental.domain.model.rent_process.Reservation;
import pl.hubertlakomski.carrental.domain.model.rent_process.ReservationReturn;
import pl.hubertlakomski.carrental.domain.repository.DepartmentRepository;
import pl.hubertlakomski.carrental.domain.repository.ReservationRepository;
import pl.hubertlakomski.carrental.domain.repository.ReservationReturnRepository;
import pl.hubertlakomski.carrental.domain.repository.StatusRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
@RequiredArgsConstructor
public class DefaultReservationReturnService implements ReservationReturnService {


    private final ReservationRepository reservationRepository;
    private final ReservationReturnRepository reservationReturnRepository;
    private final StatusRepository statusRepository;
    private final DepartmentRepository departmentRepository;

    @Transactional
    @Override
    public ReservationReturnData prepareData(Long reservationId) {

        Reservation reservation = reservationRepository.getOne(reservationId);

        if (reservation.getReturnData() != null) {
            throw new IllegalStateException("The reservation has already been returned");
        }

        ReservationReturnData reservationReturnData = new ReservationReturnData();

        reservationReturnData.setReservationId(reservation.getId());

        return reservationReturnData;
    }

    @Transactional
    @Override
    public void processData(ReservationReturnData reservationReturnData) {

        ReservationReturn reservationReturn = new ReservationReturn();

        reservationReturn.setComment(reservationReturnData.getComment());
        reservationReturn.setDepositCharge(reservationReturnData.getDepositCharge());
        reservationReturn.setRealRentalFee(reservationReturnData.getRealRentalFee());
        reservationReturn.setRealReturnDate(LocalDateTime.now());
        reservationReturn.setRealReturnDepartment(departmentRepository
                .getById(reservationReturnData.getRealReturnDepartmentId()));
//        reservationReturn.setEmployee(employee from security);

        reservationReturnRepository.save(reservationReturn);

        Reservation reservation = reservationRepository.getOne(reservationReturnData.getReservationId());

        reservation.setReturnData(reservationReturn);

        reservation.getRentData().getCar().setDepartment(reservation.getPlannedReturnDepartment());
        reservation.getRentData().getCar().setMileage(reservationReturnData.getMileage());

        Status statusAvailable = statusRepository.findByName("available");

        reservation.getRentData().getCar().setStatus(statusAvailable);
    }

    @Override
    public ReservationReturnReservationData getReservationData(Long reservationId) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        ReservationReturnReservationData data = new ReservationReturnReservationData();

        Reservation reservation = reservationRepository.getReservationById(reservationId);

        data.setRentComment(reservation.getRentData().getComment());
        data.setDeposit(reservation.getSippCode().getDeposit());
        data.setPlannedRentalFee((long) reservation.getPlannedRentalFee());
        data.setPlannedReturnDate(reservation.getPlannedReturnDate().format(formatter));
        data.setLastMileage(reservation.getRentData().getCar().getMileage());
        data.setPlannedReturnDepartment(reservation.getPlannedReturnDepartment().getCode());
        data.setReservationComment(reservation.getComment());

        return data;
    }




}
