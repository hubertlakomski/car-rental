package pl.hubertlakomski.carrental.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hubertlakomski.carrental.domain.model.car.SippCode;

public interface SippCodeRepository extends JpaRepository<SippCode, Long> {
}
