package net.thrymr.project.k.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.thrymr.project.k.entity.Employee;
import net.thrymr.project.k.enums.RoleType;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private Long id;

     private String employeeName;
    private String email;

    private RoleType roleType;


    private String password;
    private String token;


    public EmployeeDto(Employee employee) {
        this.id=employee.getId();
        this.employeeName=employee.getEmployeeName();
        this.email=employee.getEmail();
        this.roleType=employee.getRoleType();
    }
}
