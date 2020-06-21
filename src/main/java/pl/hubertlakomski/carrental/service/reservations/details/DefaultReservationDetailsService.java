package pl.hubertlakomski.carrental.service.reservations.details;

import org.springframework.stereotype.Service;
import pl.hubertlakomski.carrental.domain.model.rent_process.Reservation;
import pl.hubertlakomski.carrental.domain.model.rent_process.ReservationRent;
import pl.hubertlakomski.carrental.domain.model.rent_process.ReservationReturn;
import pl.hubertlakomski.carrental.domain.repository.*;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;

@Service
public class DefaultReservationDetailsService implements ReservationDetailsService {

    private final ReservationRepository reservationRepository;

    public DefaultReservationDetailsService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Transactional
    @Override
    public ReservationDetailsData getReservationDetailsData(Long reservationId) {

        Reservation reservation = reservationRepository.getReservationById(reservationId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        ReservationDetailsData reservationDetailsData = new ReservationDetailsData();

        reservationDetailsData.setReservationId(reservation.getId().toString());
        reservationDetailsData.setSippCode(reservation.getSippCode().getCode());

        ReservationRent reservationRent = reservation.getRentData();

        if(reservationRent!=null){
            reservationDetailsData.setRentedCar(reservationRent.getCar().getBrand());
            reservationDetailsData.setSippCode(reservationRent.getCar().getSippCode().getCode());
            reservationDetailsData.setRealRentDate(reservationRent.getRealRentDate().format(formatter));
            reservationDetailsData.setRentComment(reservationRent.getComment());
        }

        ReservationReturn reservationReturn = reservation.getReturnData();

        if(reservationReturn!=null){
            reservationDetailsData.setRealReturnDate(reservationReturn.getRealReturnDate().format(formatter));
            reservationDetailsData.setRealReturnDepartment(reservationReturn.getRealReturnDepartment().getCode());
            reservationDetailsData.setRealRentalFee(reservationReturn.getRealRentalFee().toString());
            reservationDetailsData.setDepositCharge(reservationReturn.getDepositCharge().toString());
            reservationDetailsData.setReturnComment(reservationReturn.getComment());
        }


        reservationDetailsData.setPlannedRentDate(reservation.getPlannedRentDate().format(formatter));
        reservationDetailsData.setPlannedReturnDate(reservation.getPlannedReturnDate().format(formatter));
        reservationDetailsData.setRentDepartment(reservation.getRentDepartment().getCode());
        reservationDetailsData.setPlannedReturnDepartment(reservation.getPlannedReturnDepartment().getCode());
        reservationDetailsData.setClient(reservation.getClient().getFirstName()+
                " "+reservation.getClient().getLastName());
        reservationDetailsData.setPlannedRentalFee(String.valueOf(reservation.getPlannedRentalFee()));
        reservationDetailsData.setDeposit(reservation.getSippCode().getDeposit().toString());
        reservationDetailsData.setReservationComment(reservation.getComment());


        return reservationDetailsData;
    }
}
