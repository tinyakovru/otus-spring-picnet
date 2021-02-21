package ru.tinyakov.picnet.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tinyakov.picnet.exception.InvalidDataException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserDto {
    private String nickname;
    private String pass;
    private String pass2;
    private String email;
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public boolean validate() throws InvalidDataException {
//        if(!pass.equals(pass2)) throw new InvalidDataException("passwords is not equals");
//        if(!validateEmail(email)) throw new InvalidDataException("email is not valid");;
        return true;
    }
}
