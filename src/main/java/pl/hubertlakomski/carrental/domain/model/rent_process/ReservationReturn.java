package pl.hubertlakomski.carrental.domain.model.rent_process;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.hubertlakomski.carrental.domain.model.users.Employee;
import pl.hubertlakomski.carrental.domain.model.ParentEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="reservation_return") @Getter @Setter
@ToString(exclude = {"employee"}, callSuper = true)
public class ReservationReturn extends ParentEntity {

    @OneToOne @JoinColumn(name="employee_id")
    private Employee employee; // set the currently logged in when returning the vehicle

    private LocalDateTime realReturnDate;

    private Long realRentalFee; //setting by employee
    private Long depositCharge; //seeting by employee

    private String comment;

}
