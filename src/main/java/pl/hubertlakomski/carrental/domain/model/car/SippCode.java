package pl.hubertlakomski.carrental.domain.model.car;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.hubertlakomski.carrental.domain.model.ParentEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="sipp_codes") @Getter @Setter @ToString
public class SippCode extends ParentEntity {

    @Column(nullable = false)
    private String code;

}
