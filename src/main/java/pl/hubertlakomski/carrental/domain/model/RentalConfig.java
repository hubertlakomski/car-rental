package pl.hubertlakomski.carrental.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="rental_config") @Getter @Setter @ToString
public class RentalConfig extends ParentEntity{

    private String name;
    private String domain;
    private String owner;
    private String nip;

    //Address
    private String city;
    private String country;
    private String addressLine;
    private String zipCode;

}
