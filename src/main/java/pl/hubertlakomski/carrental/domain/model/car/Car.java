package pl.hubertlakomski.carrental.domain.model.car;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.hubertlakomski.carrental.domain.model.Department;
import pl.hubertlakomski.carrental.domain.model.ParentEntity;

import javax.persistence.*;
import java.time.Year;

@Entity
@Table(name="cars") @Getter @Setter
@ToString(exclude = {"sippCode", "status", "department"}, callSuper = true)
public class Car extends ParentEntity {

    @OneToOne @JoinColumn(name = "sipp_code_id")
    private SippCode sippCode; // car-hire-centre.co.uk/sipp-code-list.html

    @Column(unique = true)
    private String plateNumber;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private Year production;

    private String color;

    @Column(nullable = false)
    private Long mileage; // must be actualized by every reservation_return

    @OneToOne @JoinColumn(name = "status_id")
    private Status status; // must be actualized by every reservation or other operation

    @OneToOne @JoinColumn(name = "department_id")
    private Department department; // must be actualized by every reservation_return
}
