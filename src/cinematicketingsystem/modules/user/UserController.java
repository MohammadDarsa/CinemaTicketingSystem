package cinematicketingsystem.modules.user;

import cinematicketingsystem.exceptions.sqlexceptions.EntityNotFoundException;
import cinematicketingsystem.models.user.customer.Customer;
import cinematicketingsystem.utils.Attributes;
import cinematicketingsystem.utils.DBManager;
import cinematicketingsystem.utils.UserManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UserController implements Initializable {

    @FXML
    private TextField userName;

    @FXML
    private TextField email;

    @FXML
    private TextField phone;

    @FXML
    private TextField balance;

    @FXML
    private TextField tickets;

    @FXML
    private Button updateBtn;

    UserManager userManager;
    DBManager dbManager;

    @FXML
    void update(ActionEvent event) {
        Attributes values = new Attributes("customer");
        values.addAttribute("username", userName.getText());
        values.addAttribute("phone", phone.getText());
        values.addAttribute("email", email.getText());
        Attributes where = new Attributes();
        where.addAttribute("id", userManager.getCustomer().getId().toString());
        try {
            dbManager.updateEntity(values, where);
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userManager = UserManager.getInstance();
        dbManager = DBManager.getInstance();
        Customer customer = userManager.getCustomer();
        userName.setText(customer.getName());
        email.setText(customer.getEmail());
        phone.setText(customer.getPhone().toString());
        balance.setText(customer.getBalance().toString());
        tickets.setText(customer.getTicketsBought().toString());
    }
}
