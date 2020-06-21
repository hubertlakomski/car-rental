package pl.hubertlakomski.carrental.service.employee.edit;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeEditData {

    private Long id;
    private String firstName;
    private String lastName;

    private String password;
    private boolean active;

    private Long departmentId;
    private List<Long> roleIdList;
}
