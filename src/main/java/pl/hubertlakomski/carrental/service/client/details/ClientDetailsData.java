package pl.hubertlakomski.carrental.service.client.details;

import lombok.Data;
import lombok.ToString;

@Data @ToString
public class ClientDetailsData {

    private String firstName;
    private String lastName;

    private String email;
    private String numberOfId;
    private String numberOfDrivingLicense;

    private String address;
    private Long addressId;
}
