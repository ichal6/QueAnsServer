package pl.lechowicz.queansserver.common.validation;

public enum InputValidationMessage {
    ID("Id should be a number greater than 0."),
    NAME("Name should be at least 3 characters long."),
    EMAIL("Email should be in format: user@domain.pl. No special signs allowed."),
    PASSWORD_EMPTY("Password empty. Please provide a password with at least one lower letter, one upper letter and one digit."),
    PASSWORD_INCORRECT("Password incorrect. Please provide a password with at least one lower letter, one upper letter and one digit."),
    NULL("Object is null");

    public final String message;

    InputValidationMessage(String message) {
        this.message = message;
    }
}
