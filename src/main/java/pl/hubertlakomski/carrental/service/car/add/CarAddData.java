package pl.hubertlakomski.carrental.service.car.add;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

@Data
@ToString
public class CarAddData {

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
