package pl.lechowicz.queansserver.common.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException() {
        super("Sorry, couldn't get the resource you are asking for.");
    }

    public ResourceNotFoundException(Message message) {
        super(message.text);
    }

    public enum Message {
        THE_SET_IS_EMPTY("Resources is empty. Please insert some values to the database"),
        THE_ENTRY_IS_NOT_EXISTS("The entry is not exists. Please provide correct id for entry.");

        public final String text;
        Message(String message) {
            this.text = message;
        }
    }
}
