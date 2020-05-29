package pl.hubertlakomski.carrental;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.hubertlakomski.carrental.domain.model.Rental;
import pl.hubertlakomski.carrental.domain.repository.RentalRepository;

@SpringBootApplication @Slf4j
public class CarRentalApplication {

    public static void main(String[] args) {

        SpringApplication.run(CarRentalApplication.class, args);
    }


}
