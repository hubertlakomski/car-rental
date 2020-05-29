package pl.hubertlakomski.carrental.domain.model.rent_process;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import pl.hubertlakomski.carrental.domain.model.car.SippCode;
import pl.hubertlakomski.carrental.domain.model.Client;
import pl.hubertlakomski.carrental.domain.model.Department;
import pl.hubertlakomski.carrental.domain.model.ParentEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="reservations") @Getter @Setter @ToString
public class Reservation extends ParentEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime created;

    @ManyToOne
    private Client client;

    @ManyToOne @JoinColumn(name="sipp_code_id")
    private SippCode sippCode;
    // when booking, the employee provides the SIPP code not concrete car

    @Column(nullable = false)
    private LocalDateTime plannedRentDate;

    @Column(nullable = false)
    private LocalDateTime plannedReturnDate;

    @OneToOne @JoinColumn(name="rent_department_id")
    private Department rentDepartment;

    @OneToOne @JoinColumn(name="return_department_id")
    private Department returnDepartment;

    private Long plannedCharge;
    private Long deposit;

    @OneToOne @JoinColumn(name="rent_data_id")
    private ReservationRent rentData = new ReservationRent();

    @OneToOne @JoinColumn(name="return_data_id")
    private ReservationReturn returnData = new ReservationReturn();
}
