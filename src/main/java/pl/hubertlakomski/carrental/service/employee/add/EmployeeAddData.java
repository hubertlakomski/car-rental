package pl.hubertlakomski.carrental.service.employee.add;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class EmployeeAddData {

    private String firstName;
    private String lastName;

    private String username;
    private String password;
    private boolean active;

    private Long departmentId;
    private List<Long> roleIdList;
}
