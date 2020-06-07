package pl.hubertlakomski.carrental.domain.model.rent_process;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import pl.hubertlakomski.carrental.domain.model.car.SippCode;
import pl.hubertlakomski.carrental.domain.model.Client;
import pl.hubertlakomski.carrental.domain.model.Department;
import pl.hubertlakomski.carrental.domain.model.ParentEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="reservations") @Getter @Setter
@ToString(exclude = {
        "client", "sippCode", "rentDepartment", "plannedReturnDepartment", "rentData", "returnData"}, callSuper = true)
public class Reservation extends ParentEntity {

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime created;

    @ManyToOne
    private Client client;

    @ManyToOne @JoinColumn(name="sipp_code_id")
    private SippCode sippCode;
    //when booking, the employee provides the SIPP code not concrete car

    @Column(nullable = false)
    private LocalDateTime plannedRentDate;

    @Column(nullable = false)
    private LocalDateTime plannedReturnDate;

    @OneToOne @JoinColumn(name="rent_department_id")
    private Department rentDepartment;

    @OneToOne @JoinColumn(name="planned_return_department_id")
    private Department plannedReturnDepartment;

    private Long plannedRentalFee;
    //rental fee: perDay(number of rental days from the beginning and end of the rental)*amount(from sipp code)

    @OneToOne @JoinColumn(name="rent_data_id")
    private ReservationRent rentData;

    @OneToOne @JoinColumn(name="return_data_id")
    private ReservationReturn returnData;

    private String comment;

    //TODO prePersist na plannedRentalFee
}
