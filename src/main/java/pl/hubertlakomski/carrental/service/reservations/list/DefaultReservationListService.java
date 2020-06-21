package pl.hubertlakomski.carrental.service.reservations.list;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.hubertlakomski.carrental.domain.model.rent_process.Reservation;
import pl.hubertlakomski.carrental.domain.model.users.Employee;
import pl.hubertlakomski.carrental.domain.model.users.User;
import pl.hubertlakomski.carrental.domain.repository.*;
import pl.hubertlakomski.carrental.service.reservations.data.ClientData;
import pl.hubertlakomski.carrental.service.reservations.data.DepartmentData;
import pl.hubertlakomski.carrental.service.reservations.data.SippCodeData;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static pl.hubertlakomski.carrental.service.car.list.DefaultCarListService.getDepartmentData;
import static pl.hubertlakomski.carrental.service.car.list.DefaultCarListService.getSippCodeData;
import static pl.hubertlakomski.carrental.service.reservations.add.DefaultReservationAddService.getClientData;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultReservationListService implements ReservationListService{

    private final ReservationRepository reservationRepository;
    private final SippCodeRepository sippCodeRepository;
    private final DepartmentRepository departmentRepository;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public List<ReservationListData> getReservations() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        List<Reservation> reservations = new ArrayList<>();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findByUsername(auth.getName());

        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

        if(authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")) ||
            authorities.stream().anyMatch(o -> (o.getAuthority().equals("ROLE_MANAGER")))) {

            reservations = reservationRepository.findAllByOrderByPlannedRentDate();
        }
        else if(authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_EMPLOYEE"))){

            Employee employee = employeeRepository.findByUserId(user.getId());

            reservations = reservationRepository
                    .findAllByRentDepartmentIdOrderByPlannedRentDate(employee.getDepartment().getId());
        }

        List<ReservationListData> reservationListData = new ArrayList<>();

        for(Reservation reservation: reservations){

            ReservationListData data = new ReservationListData();

            data.setId(reservation.getId());
            data.setPlannedRentDate(reservation.getPlannedRentDate().format(formatter));
            data.setPlannedReturnDate(reservation.getPlannedReturnDate().format(formatter));
            data.setRentDepartmentCode(reservation.getRentDepartment().getCode());
            data.setReturnDepartmentCode(reservation.getPlannedReturnDepartment().getCode());
            data.setClientFullName(reservation.getClient().getFullName());
            data.setSippCode(reservation.getSippCode().getCode());
            data.setPlannedRentalFee((long) reservation.getPlannedRentalFee());
            data.setDeposit(reservation.getDeposit());
            data.setComment(reservation.getComment());

            data.setRentDepartmentId(reservation.getRentDepartment().getId());
            data.setReturnDepartmentId(reservation.getPlannedReturnDepartment().getId());
            data.setClientId(reservation.getClient().getId());
            data.setSippCodeId(reservation.getSippCode().getId());

            data.setPlannedRentDateLDT(reservation.getPlannedRentDate());
            data.setPlannedReturnDateLDT(reservation.getPlannedReturnDate());

            reservationListData.add(data);
        }

        return reservationListData;
    }

    @Transactional
    public List<DepartmentData> getDepartments(){

        return getDepartmentData(departmentRepository);
    }

    @Transactional
    public List<SippCodeData> getSippCodes() {

        return getSippCodeData(sippCodeRepository);
    }

    @Transactional
    public List<ClientData> getClients(){

        return getClientData(clientRepository);
    }

}
