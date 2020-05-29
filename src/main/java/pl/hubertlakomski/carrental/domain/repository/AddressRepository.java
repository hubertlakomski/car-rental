package pl.hubertlakomski.carrental.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.hubertlakomski.carrental.domain.model.data.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
