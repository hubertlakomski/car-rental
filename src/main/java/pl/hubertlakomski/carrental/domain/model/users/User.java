package pl.hubertlakomski.carrental.domain.model.users;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.hubertlakomski.carrental.domain.model.ParentEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="users")
@Getter @Setter @ToString(exclude = ("password"), callSuper = true)
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends ParentEntity {

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Boolean active;

    @ManyToMany
    @JoinTable(name="users_roles",
            joinColumns = @JoinColumn(name="username",
                    referencedColumnName = "username"),
            inverseJoinColumns = @JoinColumn(name="role",
                    referencedColumnName = "name"))
    private Set<Role> roles;

}
