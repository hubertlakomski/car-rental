package pl.hubertlakomski.carrental.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hubertlakomski.carrental.domain.model.rent_process.ReservationReturn;

public interface ReservationReturnRepository extends JpaRepository<ReservationReturn, Long> {
}
