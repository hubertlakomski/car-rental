package pl.hubertlakomski.carrental.service.reservations.add;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.hubertlakomski.carrental.domain.model.Client;
import pl.hubertlakomski.carrental.domain.model.rent_process.Reservation;
import pl.hubertlakomski.carrental.domain.model.users.User;
import pl.hubertlakomski.carrental.domain.repository.*;
import pl.hubertlakomski.carrental.service.reservations.data.ClientData;
import pl.hubertlakomski.carrental.service.reservations.data.DepartmentData;
import pl.hubertlakomski.carrental.service.reservations.data.SippCodeData;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
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
    private final UserRepository userRepository;

    @Transactional
    @Override
    public Long processAddReservation(ReservationAddData reservationAddData) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUsername(authentication.getName());

        Reservation reservation = new Reservation();

        reservation.setClient(clientRepository.getOne(reservationAddData.getClientId()));
        reservation.setPlannedRentDate(LocalDateTime.parse(reservationAddData.getPlannedRentDate()));
        reservation.setPlannedReturnDate(LocalDateTime.parse(reservationAddData.getPlannedReturnDate()));
        reservation.setRentDepartment(departmentRepository.getById(reservationAddData.getRentDepartmentId()));
        reservation.setPlannedReturnDepartment(departmentRepository.getById(reservationAddData.getPlannedReturnDepartmentId()));
        reservation.setSippCode(sippCodeRepository.getOne(reservationAddData.getSippCodeId()));
        reservation.setComment(reservationAddData.getComment());
        reservation.setUser(user);

        reservation.setPlannedRentalFee((long) reservation.getPlannedRentalFee());

        log.info("Przed zapisem {}",reservationAddData.getComment());

        reservationRepository.save(reservation);

        log.info("Po zapisie {}", reservationRepository.getReservationById(reservation.getId()).getComment());

        return reservation.getId();
    }

    @Override
    public List<ClientData> getClients() {
        return getClientData(clientRepository);
    }

    public static List<ClientData> getClientData(ClientRepository clientRepository) {
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
