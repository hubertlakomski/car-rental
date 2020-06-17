package pl.hubertlakomski.carrental.service.car.list.data;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data @ToString
public class StatusData {

    private Long id;
    @NotBlank @NotNull
    private String name;
    @NotBlank @NotNull
    private String plDescription;

}
