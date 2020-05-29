package pl.hubertlakomski.carrental.domain.model.users;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.hubertlakomski.carrental.domain.model.ParentEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="roles")
@Getter @Setter @ToString(callSuper = true)
public class Role extends ParentEntity {

    private String name;
}
