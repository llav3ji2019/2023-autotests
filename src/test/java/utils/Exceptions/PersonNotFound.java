package utils.Exceptions;

public class PersonNotFound extends Exception {
    public PersonNotFound() {
        super();
    }

    public PersonNotFound(String message) {
        super(message);
    }

    public PersonNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonNotFound(Throwable cause) {
        super(cause);
    }
}
