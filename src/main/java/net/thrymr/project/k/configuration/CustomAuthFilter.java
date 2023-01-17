package net.thrymr.project.k.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.thrymr.project.k.CustomException.ApiResponse;
import net.thrymr.project.k.dto.EmployeeDto;
import net.thrymr.project.k.entity.Employee;
import net.thrymr.project.k.service.EmployeeService;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

public class CustomAuthFilter extends OncePerRequestFilter {


    @Autowired
            private EmployeeService employeeService;

    CustomAuthFilter(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    private static Logger logger = LoggerFactory.getLogger(CustomAuthFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String authToken = request.getHeader("Authorization");
            System.out.println(authToken);
            if (authToken != null) {
                Employee user = employeeService.verifyUser(generateLoginDto(authToken.split(":")[0], authToken.split(":")[1]));
                if(user != null){
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, Arrays.asList(
                            new SimpleGrantedAuthority(user.getRoleType().name())));
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    logger.info("authenticated user " + authToken.split(":")[0] + ", setting security context");
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    filterChain.doFilter(request, response);
                }else {
                    generateUnauthorisedAccess();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

            public EmployeeDto generateLoginDto (String email, String password){
                EmployeeDto dto = new EmployeeDto();
                dto.setEmail(email);
                dto.setPassword(password);
                return dto;
            }



            public ApiResponse generateUnauthorisedAccess() {
        return new ApiResponse(HttpStatus.UNAUTHORIZED.value(), "UNAUTORISED");

    }
}