package pl.hubertlakomski.carrental.domain.model.users;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.hubertlakomski.carrental.domain.model.Department;
import pl.hubertlakomski.carrental.domain.model.ParentEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="employees") @Getter @Setter @ToString
public class Employee extends ParentEntity {

    private String firstName;
    private String lastName;
    @ManyToOne
    private Department department;

}
