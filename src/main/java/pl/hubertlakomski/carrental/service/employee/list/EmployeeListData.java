package pl.hubertlakomski.carrental.service.employee.list;

import lombok.Data;
import lombok.ToString;

import java.util.Set;

@Data @ToString(exclude = {"roles"})
public class EmployeeListData {

    private Long id;
    private String firstName;
    private String lastName;

    private String username;
    private String password;
    private boolean active;

    private Long departmentId;
    private String departmentCode;
    private Long userId;
    private Set<String> roles;

}
