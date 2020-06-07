package pl.hubertlakomski.carrental.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hubertlakomski.carrental.domain.model.rent_process.Reservation;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByRentDepartmentIdAndRentDataIsNullOrderByPlannedRentDate(Long id);
    List<Reservation> findAllByPlannedReturnDepartmentIdAndReturnDataIsNullOrderByPlannedReturnDate(Long id);

    Reservation getReservationById(Long id);

}
