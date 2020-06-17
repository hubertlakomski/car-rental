package pl.hubertlakomski.carrental.service.reservations.add;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.hubertlakomski.carrental.domain.model.Client;
import pl.hubertlakomski.carrental.domain.model.Department;
import pl.hubertlakomski.carrental.domain.model.car.SippCode;
import pl.hubertlakomski.carrental.domain.model.rent_process.Reservation;
import pl.hubertlakomski.carrental.domain.repository.ClientRepository;
import pl.hubertlakomski.carrental.domain.repository.DepartmentRepository;
import pl.hubertlakomski.carrental.domain.repository.ReservationRepository;
import pl.hubertlakomski.carrental.domain.repository.SippCodeRepository;
import pl.hubertlakomski.carrental.service.reservations.add.data.ClientData;
import pl.hubertlakomski.carrental.service.reservations.add.data.DepartmentData;
import pl.hubertlakomski.carrental.service.reservations.add.data.SippCodeData;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import static pl.hubertlakomski.carrental.service.car.list.DefaultCarListService.getDepartmentData;
import static pl.hubertlakomski.carrental.service.car.list.DefaultCarListService.getSippCodeData;

@Service @Slf4j
@RequiredArgsConstructor
public class DefaultReservationAddService implements ReservationAddService {

    private final ClientRepository clientRepository;
    private final DepartmentRepository departmentRepository;
    private final SippCodeRepository sippCodeRepository;
    private final ReservationRepository reservationRepository;

    @Transactional
    @Override
    public Long processAddReservation(ReservationAddData reservationAddData) {

        Reservation reservation = new Reservation();

        reservation.setClient(clientRepository.getOne(reservationAddData.getClientId()));
        reservation.setPlannedRentDate(LocalDateTime.parse(reservationAddData.getPlannedRentDate()));
        reservation.setPlannedReturnDate(LocalDateTime.parse(reservationAddData.getPlannedReturnDate()));
        reservation.setRentDepartment(departmentRepository.getById(reservationAddData.getRentDepartmentId()));
        reservation.setPlannedReturnDepartment(departmentRepository.getById(reservationAddData.getPlannedReturnDepartmentId()));
        reservation.setSippCode(sippCodeRepository.getOne(reservationAddData.getSippCodeId()));
        reservation.setComment(reservationAddData.getComment());

        reservation.setPlannedRentalFee((long) getPlannedRentalFee(
                reservation.getPlannedRentDate(),
                reservation.getPlannedReturnDate(),
                reservation.getSippCode()));

        log.info("Przed zapisem {}",reservationAddData.getComment());

        reservationRepository.save(reservation);

        log.info("Po zapisie {}", reservationRepository.getReservationById(reservation.getId()).getComment());

        return reservation.getId();
    }

    private double getPlannedRentalFee(LocalDateTime start, LocalDateTime end, SippCode sippCode){

        double periodOfRentalDays  = getPeriodOfRentalDays(start, end);
        Long amountForDay = sippCode.getPerDayCharge();

        return periodOfRentalDays*amountForDay;
    }

    private double getPeriodOfRentalDays(LocalDateTime start, LocalDateTime end){

        long periodOfHours = ChronoUnit.HOURS.between(start, end);

        double period = periodOfHours/24.0;

        if(period < 1){
            return 1;
        }
        else if(period%1==0){
            return period;
        }
        else{
            return Math.ceil(period);
        }
    }

    @Transactional
    @Override
    public List<ClientData> getClients() {

        List<Client> clients = clientRepository.findAll();
        List<ClientData> clientDataList = new ArrayList<>();

        for(Client client: clients){

            ClientData clientData = new ClientData();

            clientData.setFirstName(client.getFirstName());
            clientData.setLastName(client.getLastName());
            clientData.setId(client.getId());

            clientDataList.add(clientData);
        }

        return clientDataList;
    }

    @Transactional
    @Override
    public List<DepartmentData> getDepartments() {

        return getDepartmentData(departmentRepository);
    }

    @Transactional
    @Override
    public List<SippCodeData> getSippCodes() {

        return getSippCodeData(sippCodeRepository);
    }


}
