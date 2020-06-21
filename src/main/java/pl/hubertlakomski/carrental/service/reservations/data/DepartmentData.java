package pl.hubertlakomski.carrental.service.reservations.data;

import lombok.Data;
import lombok.ToString;
import pl.hubertlakomski.carrental.domain.model.data.Address;

@Data
@ToString
public class DepartmentData {

    private Long id;
    private String code;
    private Address address;

    public String getDescription(){

        return code+" - "+address.getCity()+", "+address.getAddressLine();
    }
}
