package cinematicketingsystem.modules.login.loginservice;

import cinematicketingsystem.exceptions.loginexceptions.PasswordRegexException;
import cinematicketingsystem.exceptions.loginexceptions.UserNotFoundException;
import cinematicketingsystem.exceptions.loginexceptions.UsernameRegexException;

public interface LoginService {
    void login(String username, String password) throws UserNotFoundException, UsernameRegexException, PasswordRegexException;
}
