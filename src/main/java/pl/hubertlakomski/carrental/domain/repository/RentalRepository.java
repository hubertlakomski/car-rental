package pl.hubertlakomski.carrental.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hubertlakomski.carrental.domain.model.RentalConfig;

public interface RentalRepository extends JpaRepository<RentalConfig, Long> {

    RentalConfig findFirstBy();
}
