package pl.hubertlakomski.carrental.domain.model.users;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.hubertlakomski.carrental.domain.model.ParentEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
@Getter @Setter @ToString(exclude = {"password", "roles"}, callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends ParentEntity {

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Boolean active;

    @CollectionTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "username", referencedColumnName = "username")
    )
    @ElementCollection
    @Column(name = "role")
    private Set<String> roles;

}
