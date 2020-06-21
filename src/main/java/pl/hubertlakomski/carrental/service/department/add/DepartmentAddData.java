package pl.hubertlakomski.carrental.service.department.add;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.*;

@Data
@ToString
public class DepartmentAddData {

    @NotNull @NotBlank @Size(min = 7, max=12)
    private String code;

    /* address */
    @NotBlank @NotNull
    private String country;
    @NotBlank @NotNull
    private String city;
    @NotBlank @NotNull
    private String addressLine;
    private String zipCode;

}
