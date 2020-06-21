package pl.hubertlakomski.carrental.service.client.add;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data @ToString
public class ClientAddData {

    @Email
    private String email;
    @NotNull @NotBlank
    private String firstName;
    @NotNull @NotBlank
    private String lastName;
    @NotNull @NotBlank
    private String numberOfDrivingLicence;
    @NotNull @NotBlank
    private String numberOfId;
    @NotNull @NotBlank
    private String country;
    @NotNull @NotBlank
    private String city;
    @NotNull @NotBlank
    private String addressLine;
    private String zippCode;

}
