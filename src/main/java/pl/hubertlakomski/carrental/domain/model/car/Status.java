package pl.hubertlakomski.carrental.domain.model.car;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.hubertlakomski.carrental.domain.model.ParentEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name="statuses") @Getter @Setter @ToString
public class Status extends ParentEntity {

    @Column(nullable = false)
    private String name;
    private String plDescription;

}
