package pl.hubertlakomski.carrental.service.client.add;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@ToString
public class ClientAddData {

    @Email
    private String email;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String numberOfDrivingLicence;
    @NotNull
    private String numberOfId;
    @NotNull
    private String country;
    @NotNull
    private String city;
    @NotNull
    private String addressLine;
    private String zippCode;

}