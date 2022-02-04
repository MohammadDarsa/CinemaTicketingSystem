package cinematicketingsystem.modules.login.loginservice;

import cinematicketingsystem.exceptions.loginexceptions.PasswordRegexException;
import cinematicketingsystem.exceptions.loginexceptions.UserNotFoundException;
import cinematicketingsystem.exceptions.loginexceptions.UsernameRegexException;
import cinematicketingsystem.models.user.customer.Customer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginServiceProxy implements LoginService{

    LoginServiceExecutor loginServiceExecutor;

    //at least 1 digit, at least 1 lower case, at least 1 upper case, at least 1 symbol, from 8 to 20 characters
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    //at least 3 characters, a-z capital or small, digits 0 to 9
    private static final String USERNAME_PATTERN = "[a-zA-Z0-9]{3,}";

    private static final Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
    private static final Pattern usernamePattern = Pattern.compile(USERNAME_PATTERN);


    public LoginServiceProxy() {
        this.loginServiceExecutor = new LoginServiceExecutor();
    }


    @Override
    public Customer login(String username, String password) throws UserNotFoundException, UsernameRegexException, PasswordRegexException {
        if(!validateUsername(username)) throw new UsernameRegexException("Username doesn't follow the pattern try again!");
        if(!validatePassword(password)) throw new PasswordRegexException("Password doesn't follow the pattern try again!");
        return loginServiceExecutor.login(username, password);
    }

    private boolean validatePassword(String password) {
        Matcher matcher = passwordPattern.matcher(password);
        return matcher.matches();
    }

    private boolean validateUsername(String username) {
        Matcher matcher = usernamePattern.matcher(username);
        return matcher.matches();
    }
}
