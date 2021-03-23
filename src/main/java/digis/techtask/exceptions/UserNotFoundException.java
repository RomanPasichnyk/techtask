package digis.techtask.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String userId) {
        super(String.format("User with id %s was not found", userId));
    }
}
