package pl.hubertlakomski.carrental.service.configuration.heroku;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.hubertlakomski.carrental.domain.model.Client;
import pl.hubertlakomski.carrental.domain.model.Department;
import pl.hubertlakomski.carrental.domain.model.car.Car;
import pl.hubertlakomski.carrental.domain.model.car.SippCode;
import pl.hubertlakomski.carrental.domain.model.car.Status;
import pl.hubertlakomski.carrental.domain.model.data.Address;
import pl.hubertlakomski.carrental.domain.model.rent_process.Reservation;
import pl.hubertlakomski.carrental.domain.model.users.Employee;
import pl.hubertlakomski.carrental.domain.model.users.Role;
import pl.hubertlakomski.carrental.domain.model.users.User;
import pl.hubertlakomski.carrental.domain.repository.*;
import pl.hubertlakomski.carrental.service.car.add.CarAddService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Profile("heroku")
@Service
@RequiredArgsConstructor
public class HerokuSetupService implements CommandLineRunner {

    private final DepartmentRepository departmentRepository;
    private final ClientRepository clientRepository;
    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SippCodeRepository sippCodeRepository;
    private final PasswordEncoder passwordEncoder;
    private final StatusRepository statusRepository;

    private final AddressRepository addressRepository;

    @Override
    public void run(String... args) throws Exception {

        //roles

        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        roleRepository.save(adminRole);

        Role managerRole = new Role();
        managerRole.setName("ROLE_MANAGER");
        roleRepository.save(managerRole);

        Role employeeRole = new Role();
        employeeRole.setName("ROLE_EMPLOYEE");
        roleRepository.save(employeeRole);

        //users

        Set<String> adminRoles = new HashSet<>();

        adminRoles.add("ROLE_ADMIN");
        adminRoles.add("ROLE_MANAGER");
        adminRoles.add("ROLE_EMPLOYEE");

        User admin = new User();

        admin.setActive(true);
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("123"));
        admin.setRoles(adminRoles);
        userRepository.save(admin);

        //sipp codes

        SippCode mbmr = new SippCode();
        mbmr.setCode("MBMR");
        mbmr.setDeposit((long) 800);
        mbmr.setPerDayCharge((long) 120);
        sippCodeRepository.save(mbmr);

        SippCode cdar = new SippCode();
        cdar.setCode("CDAR");
        cdar.setDeposit((long) 1200);
        cdar.setPerDayCharge((long) 200);
        sippCodeRepository.save(cdar);

        //statuses

        Status available = new Status();
        available.setName("available");
        available.setPlDescription("dostępny");
        statusRepository.save(available);

        Status rented = new Status();
        rented.setName("rented");
        rented.setPlDescription("wynajęty");
        statusRepository.save(rented);

        Status inRepair = new Status();
        inRepair.setName("inRepair");
        inRepair.setPlDescription("w naprawie");
        statusRepository.save(inRepair);

        Status onInspection = new Status();
        onInspection.setName("onInspection");
        onInspection.setPlDescription("przegląd");
        statusRepository.save(onInspection);

        Status damaged = new Status();
        damaged.setName("damaged");
        damaged.setPlDescription("uszkodzony");
        statusRepository.save(damaged);

        Status off = new Status();
        off.setName("off");
        off.setPlDescription("wyłączony z użytku");
        statusRepository.save(off);

        //departments

        Department wroLot = new Department();
        Address wroLotAddress = new Address();

        wroLotAddress.setCountry("PL");
        wroLotAddress.setCity("Wrocław");
        wroLotAddress.setZipCode("50-000");
        wroLotAddress.setAddressLine("ul. Graniczna 191");
        addressRepository.save(wroLotAddress);

        wroLot.setCode("WRO-LOT");
        wroLot.setAddress(wroLotAddress);
        departmentRepository.save(wroLot);

        //cars

        Car ww50789 = new Car();
        ww50789.setPlateNumber("WW50789");
        ww50789.setBrand("Volvo");
        ww50789.setModel("XC90");
        ww50789.setColor("czarny");
        ww50789.setProduction(2020);
        ww50789.setMileage((long) 100);
        ww50789.setSippCode(cdar);
        ww50789.setDepartment(wroLot);
        ww50789.setStatus(available);

        carRepository.save(ww50789);


        //clients

        Client janKowalski = new Client();
        Address janKowalskiAddress = new Address();

        janKowalskiAddress.setCountry("PL");
        janKowalskiAddress.setCity("Warszawa");
        janKowalskiAddress.setZipCode("00-200");
        janKowalskiAddress.setAddressLine("ul. Poleczki 35");

        addressRepository.save(janKowalskiAddress);

        janKowalski.setAddress(janKowalskiAddress);
        janKowalski.setFirstName("Jan");
        janKowalski.setLastName("Kowalski");
        janKowalski.setEmail("jan.kowalski@gmail.com");
        janKowalski.setNumberOfDrivingLicense("1890/202/2002");
        janKowalski.setNumberOfId("AAA000000");

        clientRepository.save(janKowalski);

    }
}
