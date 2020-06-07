package pl.hubertlakomski.carrental.service.reservations.add;

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

@Service @Slf4j
public class DefaultReservationAddService implements ReservationAddService {

    private final ClientRepository clientRepository;
    private final DepartmentRepository departmentRepository;
    private final SippCodeRepository sippCodeRepository;
    private final ReservationRepository reservationRepository;

    public DefaultReservationAddService(ClientRepository clientRepository, DepartmentRepository departmentRepository, SippCodeRepository sippCodeRepository, ReservationRepository reservationRepository) {
        this.clientRepository = clientRepository;
        this.departmentRepository = departmentRepository;
        this.sippCodeRepository = sippCodeRepository;
        this.reservationRepository = reservationRepository;
    }

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

        reservationRepository.save(reservation);

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

        List<Department> departments = departmentRepository.findAll();
        List<DepartmentData> departmentDataList = new ArrayList<>();

        for(Department department: departments){

            DepartmentData departmentData = new DepartmentData();

            departmentData.setAddress(department.getAddress());
            departmentData.setCode(department.getCode());
            departmentData.setId(department.getId());

            departmentDataList.add(departmentData);
        }

        return departmentDataList;
    }

    @Transactional
    @Override
    public List<SippCodeData> getSippCodes() {

        List<SippCode> sippCodes = sippCodeRepository.findAll();
        List<SippCodeData> sippCodeDataList = new ArrayList<>();

        for(SippCode sippCode: sippCodes){

            SippCodeData sippCodeData = new SippCodeData();

            sippCodeData.setCode(sippCode.getCode());
            sippCodeData.setId(sippCode.getId());

            sippCodeDataList.add(sippCodeData);
        }

        return sippCodeDataList;
    }


}
