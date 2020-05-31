package pl.hubertlakomski.carrental.domain.model.rent_process;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.hubertlakomski.carrental.domain.model.car.Car;
import pl.hubertlakomski.carrental.domain.model.users.Employee;
import pl.hubertlakomski.carrental.domain.model.ParentEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Table(name="reservation_rent") @Getter @Setter
@ToString(exclude = {"reservation", "employee", "car", "comment"}, callSuper = true)
public class ReservationRent extends ParentEntity {

    @OneToOne @JoinColumn(name="reservation_id")
    private Reservation reservation;

    @OneToOne @JoinColumn(name="employee_id")
    private Employee employee; // set the currently logged in when the vehicle is released

    @ManyToOne
    private Car car;

    private LocalDateTime realRentDate;

    private String comment;

}
