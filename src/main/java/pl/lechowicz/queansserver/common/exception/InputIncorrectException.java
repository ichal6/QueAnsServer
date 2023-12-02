package pl.lechowicz.queansserver.common.exception;

import pl.lechowicz.queansserver.common.validation.InputValidationMessage;

public class InputIncorrectException extends Exception {
    public InputIncorrectException(InputValidationMessage inputValidationMessage) {
        super(inputValidationMessage.message);
    }
}
