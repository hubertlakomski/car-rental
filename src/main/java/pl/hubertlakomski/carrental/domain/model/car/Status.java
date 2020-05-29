package pl.hubertlakomski.carrental.domain.model.car;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.hubertlakomski.carrental.domain.model.ParentEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="statuses") @Getter @Setter @ToString
public class Status extends ParentEntity {

    private boolean rented;
    private boolean available;
    private boolean damaged;
    private boolean inRepair;
    private boolean onInspection;
}
