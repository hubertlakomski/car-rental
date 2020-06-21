package pl.hubertlakomski.carrental.service.department.list;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data @ToString
public class DepartmentListData {

    private Long id;
    private String code;

    /* address */
    private String country;
    private String city;
    private String addressLine;
    private String zipCode;
}
