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
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImplementation implements EmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;


    private PasswordConversion passwordConversion;

    public ApiResponse  singUp(EmployeeDto employeeDto){
        Employee employee = new Employee();

        employee.setEmployeeName(employeeDto.getEmployeeName());
        employee.setEmail(employeeDto.getEmail());
        employee.setRoleType(employeeDto.getRoleType());
        employee.setPassword(employeeDto.getPassword());
        employeeRepository.save(employee);
        return new ApiResponse( HttpStatus.OK.value(), "Registration is done", employee);

    }

    @Override
    public Employee verifyUser(EmployeeDto employeeDto) {
        Employee user = employeeRepository.findByEmail(employeeDto.getEmail());
//        if (user == null) {
//            return null;
//        }
        if (employeeDto.getPassword().equals(user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public ApiResponse getAll() {
      return new ApiResponse(HttpStatus.OK.value(), employeeRepository.findAll().stream().toList());
    }

}

