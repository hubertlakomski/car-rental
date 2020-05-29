package pl.hubertlakomski.carrental.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hubertlakomski.carrental.domain.model.rent_process.ReservationRent;

public interface ReservationRentRepository extends JpaRepository<ReservationRent, Long> {
}
