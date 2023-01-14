package net.thrymr.project.k.dto;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Base64;
@Component
public class PasswordConversion {
    public String encoder(String password) {

        byte[] encrypt = Base64.getEncoder().encode(password.getBytes());
        return new String(encrypt);
    }

    public String decoder(byte[] encrypt) {
        return Arrays.toString(Base64.getDecoder().decode(encrypt));
    }

    public Boolean matches(String dtoPassword,String entityPassword) {
        if (dtoPassword.equals(entityPassword)) {
            return true;

        } else return false;

    }
}