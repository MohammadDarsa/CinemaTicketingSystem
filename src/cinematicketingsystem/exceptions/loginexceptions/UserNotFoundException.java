package cinematicketingsystem.exceptions.loginexceptions;

public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String msg) {
        super(msg);
    }
}
