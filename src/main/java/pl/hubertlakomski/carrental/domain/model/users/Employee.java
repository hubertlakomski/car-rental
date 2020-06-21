package pl.hubertlakomski.carrental.domain.model.users;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.hubertlakomski.carrental.domain.model.Department;
import pl.hubertlakomski.carrental.domain.model.ParentEntity;

import javax.persistence.*;

@Entity
@Table(name="employees") @Getter @Setter
@ToString(exclude = {"department", "user"}, callSuper = true)
public class Employee extends ParentEntity {

    private String firstName;
    private String lastName;
    @ManyToOne
    private Department department;

    @OneToOne @JoinColumn(name = "user_id")
    private User user;

}
