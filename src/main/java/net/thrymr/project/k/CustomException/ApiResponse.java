package net.thrymr.project.k.CustomException;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import net.thrymr.project.k.dto.EmployeeDto;


import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)

@Getter
@Setter



public class ApiResponse {

    private int code;

    private String message;

    private Object payLoad;



    public ApiResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public ApiResponse(int code, Object payLoad) {
        this.code = code;
        this.payLoad = payLoad;
    }

    public ApiResponse(){
        super();
    }

}
