package net.thrymr.project.k.service;


import com.nimbusds.jose.JOSEException;
import net.thrymr.project.k.CustomException.ApiResponse;
import net.thrymr.project.k.dto.EmployeeDto;
import net.thrymr.project.k.entity.Employee;

public interface EmployeeService {

    public ApiResponse singUp(EmployeeDto employeeDto);

    Employee verifyUser(EmployeeDto employeeDto);

    ApiResponse getAll();

    public ApiResponse signIn(EmployeeDto employeeDto) throws JOSEException;
}
