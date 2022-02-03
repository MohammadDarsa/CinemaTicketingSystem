package cinematicketingsystem.modules.login.loginservice;

import cinematicketingsystem.exceptions.loginexceptions.UserNotFoundException;
import cinematicketingsystem.models.user.customer.Customer;
import cinematicketingsystem.utils.DBManager;
import cinematicketingsystem.utils.SceneManager;

import java.io.IOException;
import java.util.Optional;

public class LoginServiceExecutor implements LoginService {
    private DBManager dbManager;
    private SceneManager sceneManager;

    public LoginServiceExecutor() {
        dbManager = DBManager.getInstance();
        sceneManager = SceneManager.getInstance();
    }

    @Override
    public void login(String username, String password) throws UserNotFoundException {
        Optional<Customer> customer = dbManager.selectAll(Customer.class).stream().filter(user -> user.getName().equals(username) && user.getPassword().equals(password)).findAny();
        if(customer.isPresent()) {
            try {
                sceneManager.switchScene(null , "/view/movieSelector.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //TODO: throw an exception
            throw new UserNotFoundException("Please check the entered fields!");
        }
    }
}
