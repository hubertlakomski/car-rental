package pl.hubertlakomski.carrental.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.hubertlakomski.carrental.domain.model.ParentEntity;
import pl.hubertlakomski.carrental.domain.model.data.Address;
import pl.hubertlakomski.carrental.domain.model.rent_process.Reservation;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
@Table(name="clients") @Getter @Setter
@ToString(exclude = {"address"}, callSuper = true)
public class Client extends ParentEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String numberOfId;

    @Column(nullable = false)
    private String numberOfDrivingLicense;

    @Email @Column(nullable = false, unique = true)
    private String email;

    @OneToOne @JoinColumn(name="address_id")
    private Address address;

    public String getFullName(){
       return firstName+" "+lastName;
    }

}
