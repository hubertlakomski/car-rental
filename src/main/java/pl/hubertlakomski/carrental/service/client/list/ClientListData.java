package pl.hubertlakomski.carrental.service.client.list;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ClientListData {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String numberOfDrivingLicence;
    private String numberOfId;
    private String country;
    private String city;
    private String addressLine;
    private String zippCode;

}
