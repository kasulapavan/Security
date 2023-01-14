package net.thrymr.project.k.service;


import net.thrymr.project.k.CustomException.ApiResponse;
import net.thrymr.project.k.dto.EmployeeDto;
import net.thrymr.project.k.entity.Employee;

public interface EmployeeService {

    public ApiResponse singUp(EmployeeDto employeeDto);

    Employee verifyUser(EmployeeDto employeeDto);

    ApiResponse getAll();
}
