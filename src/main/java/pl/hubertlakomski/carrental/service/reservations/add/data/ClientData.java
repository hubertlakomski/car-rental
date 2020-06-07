package pl.hubertlakomski.carrental.service.reservations.add.data;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ClientData {

    private Long id;
    private String firstName;
    private String lastName;

    public String getDescription(){

        return "Id: "+id+", "+firstName+" "+lastName;
    }

}
