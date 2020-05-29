package pl.hubertlakomski.carrental.domain.model.rent_process;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.hubertlakomski.carrental.domain.model.users.Employee;
import pl.hubertlakomski.carrental.domain.model.ParentEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="reservation_return") @Getter @Setter @ToString
public class ReservationReturn extends ParentEntity {

    @OneToOne @JoinColumn(name="reservation_id")
    private Reservation reservation;

    @OneToOne @JoinColumn(name="employee_id")
    private Employee employee;

    private LocalDateTime returnDate;

    private Long surcharge;
    private Long depositCharge;

    private String comment;

}
