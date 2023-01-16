package net.thrymr.project.k.service.impl;

import net.thrymr.project.k.CustomException.ApiResponse;
import net.thrymr.project.k.dto.EmployeeDto;
import net.thrymr.project.k.dto.PasswordConversion;
import net.thrymr.project.k.entity.Employee;
import net.thrymr.project.k.repository.EmployeeRepository;
import net.thrymr.project.k.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImplementation implements EmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;

@Autowired
    private PasswordConversion passwordConversion;

public ApiResponse signIn(EmployeeDto employeeDto) {
    Employee employee = employeeRepository.findByEmail(employeeDto.getEmail());

    if (employee == null) {
        return new ApiResponse(HttpStatus.UNAUTHORIZED.value(),"Email id is wrong");
    }
    if (passwordConversion.matches(passwordConversion.encoder(employeeDto.getPassword()), employee.getPassword())) {
        return new ApiResponse(HttpStatus.OK.value(), entityToDto(employee));
    } else {
        return new ApiResponse(HttpStatus.UNAUTHORIZED.value(), "Password is wrong");
    }

}


    public ApiResponse  singUp(EmployeeDto employeeDto){
        Employee employee = new Employee();

        employee.setEmployeeName(employeeDto.getEmployeeName());
        employee.setEmail(employeeDto.getEmail());
        employee.setRoleType(employeeDto.getRoleType());
        employee.setPassword(passwordConversion.encoder(employeeDto.getPassword()));
        employeeRepository.save(employee);
        return new ApiResponse( HttpStatus.OK.value(), "Registration is done");

    }

    @Override
    public Employee verifyUser(EmployeeDto employeeDto) {
        Employee user = employeeRepository.findByEmail(employeeDto.getEmail());
        if(passwordConversion.matches(employeeDto.getPassword(), user.getPassword())){
            return user;
        } else {
            return null;
        }
    }

    @Override
    public ApiResponse getAll() {
      return new ApiResponse(HttpStatus.OK.value(), employeeRepository.findAll().stream().toList());
    }

public EmployeeDto entityToDto(Employee employee){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setEmployeeName(employee.getEmployeeName());
        employeeDto.setRoleType(employee.getRoleType());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPassword(employee.getPassword());
        return employeeDto;

}
}

