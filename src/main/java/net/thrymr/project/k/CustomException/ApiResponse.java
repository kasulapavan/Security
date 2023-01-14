package net.thrymr.project.k.CustomException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    private int code;

    private String message;

    private Object payLoad;




//
//    public ApiResponse(int code, String message) {
//        this.code = code;
//        this.message = message;
//    }
//
    public ApiResponse(int code, Object payLoad) {
        this.code = code;
        this.payLoad = payLoad;
    }
}
