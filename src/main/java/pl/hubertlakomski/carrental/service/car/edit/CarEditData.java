package pl.hubertlakomski.carrental.service.car.edit;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;

@Data
@ToString
public class CarEditData {

    @Column(nullable = false)
    private Long id;
    private String plateNumber;
    private String brand;
    private String model;
    private String color;
    @Range(min=1900, max=2020)
    private Integer production;
    private Long mileage;
    private Long sippCodeId;
    private Long departmentId;
    private Long statusId;
}
