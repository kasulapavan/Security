package net.thrymr.project.k.controller;

import net.thrymr.project.k.CustomException.ApiResponse;
import net.thrymr.project.k.dto.EmployeeDto;
import net.thrymr.project.k.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmployeeController {


    @Autowired
    private EmployeeService userService;

    @PostMapping("/save-signup")
    public ApiResponse signUp(@RequestBody EmployeeDto employeeDto){
        return userService.singUp(employeeDto);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','MANAGER')")
    @GetMapping("/get-all")
    public ApiResponse getAll(){
        return userService.getAll();
    }
}