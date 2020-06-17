package pl.hubertlakomski.carrental.service.car.list;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CarListData {

    private Long id;
    private String plateNumber;
    private String brand;
    private String model;
    private String color;
    private Integer production;
    private Long mileage;
    private String sippCodeDsc;
    private Long sippCodeId;
    private String departmentDsc;
    private Long departmentId;
    private String statusDsc;
    private Long statusId;
}
