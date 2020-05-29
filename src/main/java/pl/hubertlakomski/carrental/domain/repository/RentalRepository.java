package pl.hubertlakomski.carrental.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hubertlakomski.carrental.domain.model.Rental;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    Rental findFirstBy();
}
