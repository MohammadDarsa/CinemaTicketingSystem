package cinematicketingsystem.modules.login.loginservice;

import cinematicketingsystem.exceptions.loginexceptions.PasswordRegexException;
import cinematicketingsystem.exceptions.loginexceptions.UserNotFoundException;
import cinematicketingsystem.exceptions.loginexceptions.UsernameRegexException;
import cinematicketingsystem.models.user.customer.Customer;

public interface LoginService {
    Customer login(String username, String password) throws UserNotFoundException, UsernameRegexException, PasswordRegexException;
}
