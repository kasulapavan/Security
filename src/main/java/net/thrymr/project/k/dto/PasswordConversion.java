package net.thrymr.project.k.dto;

import org.springframework.stereotype.Component;


import java.util.Base64;
@Component
public class PasswordConversion {

    public String encoder(String password) {
        byte[] encrypt = Base64.getEncoder().encode(password.getBytes());
        return new String(encrypt);
    }

    public String decoder(String encrypt) {
        byte[] decoder = Base64.getDecoder().decode(encrypt);
return new String(decoder);
    }

    public Boolean matches(String dtoPassword,String entityPassword) {
        if (dtoPassword.equals(entityPassword)) {
            return true;

        } else return false;

    }
}