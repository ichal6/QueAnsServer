package pl.lechowicz.queansserver.common.validation;

import org.springframework.stereotype.Component;
import pl.lechowicz.queansserver.common.exception.InputIncorrectException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class InputValidator {
    public void checkName(String name) throws InputIncorrectException {
        if (name == null || name.length() <= 3) {
            throw new InputIncorrectException(InputValidationMessage.NAME);
        }
    }

    public void checkEmail(String email) throws InputIncorrectException {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new InputIncorrectException(InputValidationMessage.EMAIL);
        }
    }

    public void checkPassword(String password) throws InputIncorrectException {
        // TODO - should add more password validation logic and message to enum
        if (password == null || password.isBlank()) {
            throw new InputIncorrectException(InputValidationMessage.PASSWORD);
        }
    }

    public <T> void isNotNull(T object) throws InputIncorrectException {
        if (object == null) {
            throw new InputIncorrectException(InputValidationMessage.NULL);
        }
    }
}
