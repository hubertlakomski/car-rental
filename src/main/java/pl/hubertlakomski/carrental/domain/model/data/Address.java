package pl.hubertlakomski.carrental.domain.model.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.hubertlakomski.carrental.domain.model.ParentEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="addresses") @Getter @Setter @ToString
public class Address extends ParentEntity {

    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String addressLine;
    private String zipCode;
}
