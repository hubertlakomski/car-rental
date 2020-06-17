package pl.hubertlakomski.carrental.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hubertlakomski.carrental.domain.model.car.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {

    Status findByName(String name);

}
