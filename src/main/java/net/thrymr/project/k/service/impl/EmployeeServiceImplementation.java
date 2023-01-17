package net.thrymr.project.k.service.impl;

import com.nimbusds.jose.JOSEException;
import net.thrymr.project.k.CustomException.ApiResponse;
import net.thrymr.project.k.configuration.JwtTokenUtils;
import net.thrymr.project.k.dto.EmployeeDto;
import net.thrymr.project.k.dto.PasswordConversion;
import net.thrymr.project.k.entity.Employee;
import net.thrymr.project.k.repository.EmployeeRepository;
import net.thrymr.project.k.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;



@Service
public class EmployeeServiceImplementation implements EmployeeService {


    @Autowired
    private EmployeeRepository employeeRepository;


    @Autowired
    private JwtTokenUtils jwtTokenUtils;

@Autowired
    private PasswordConversion passwordConversion;

public ApiResponse signIn(EmployeeDto employeeDto) throws JOSEException {
    Employee employee = employeeRepository.findByEmail(employeeDto.getEmail());

    if (employee == null) {
        return new ApiResponse(HttpStatus.UNAUTHORIZED.value(),"Email id is wrong");
    }
    if (passwordConversion.matches(passwordConversion.encoder(employeeDto.getPassword()), employee.getPassword())) {
      EmployeeDto employeeDto1 = new EmployeeDto(employee);
      employeeDto1.setToken(jwtTokenUtils.getToken(employee));
        return new ApiResponse(HttpStatus.OK.value(), employeeDto1);
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
        if(user == null){
            return null;
        }
        if(passwordConversion.matches(employeeDto.getPassword(), user.getPassword())){
            EmployeeDto response = new EmployeeDto(user);
            response.setToken(employeeDto.getEmail()+":"+employeeDto.getPassword());
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

