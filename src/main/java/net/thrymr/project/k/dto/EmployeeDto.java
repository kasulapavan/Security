package net.thrymr.project.k.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.thrymr.project.k.entity.Employee;
import net.thrymr.project.k.enums.RoleType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private Long id;

     private String employeeName;
    private String email;

    private RoleType roleType;


    private String password;



}
