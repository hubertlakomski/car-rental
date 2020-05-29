package pl.hubertlakomski.carrental.domain.model;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter @Setter @EqualsAndHashCode(of = "id") @ToString(of = "id")
@MappedSuperclass
public abstract class ParentEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
