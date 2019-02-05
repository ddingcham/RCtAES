package example.model;

public class IDException extends IllegalArgumentException {
    public IDException() {
        super("id must be defined as positive");
    }

    public IDException(String message) {
        super(message);
    }
}
