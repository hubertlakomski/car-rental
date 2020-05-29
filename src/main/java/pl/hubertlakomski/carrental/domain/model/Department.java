package pl.hubertlakomski.carrental.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.hubertlakomski.carrental.domain.model.car.Car;
import pl.hubertlakomski.carrental.domain.model.data.Address;
import pl.hubertlakomski.carrental.domain.model.users.Employee;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="departments") @Getter @Setter @ToString
public class Department extends ParentEntity{

    @Column(nullable = false, unique = true)
    private String code; // format WRO-01 (Wrocław - 01) etc.

    @OneToOne @JoinColumn(name="address_id")
    private Address address;

    @OneToMany(mappedBy = "department")
    private Set<Employee> employees = new HashSet<>();

    @OneToMany(mappedBy = "department")
    private Set<Car> cars = new HashSet<>();
}
