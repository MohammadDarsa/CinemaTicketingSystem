package cinematicketingsystem.models;

import cinematicketingsystem.annotations.Col;
import cinematicketingsystem.annotations.ID;
import cinematicketingsystem.annotations.Table;

import java.util.Objects;

//customer table <=> Customer

@Table(name = "customer")
public class Customer {
    @ID
    @Col(name = "id_customer", insertIgnore = true)
    private String id;
    @Col(name = "username_customer", updateIgnore = true)
    private String userName;
    @Col(name = "password_customer")
    private String password;
    @Col(name = "email_customer")
    private String email;
    @Col(name = "money_customer")
    private String money;

    public Customer() {
    }

    public Customer(String userName, String password, String email, String money) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.money = money;
    }

    public Customer(String id, String userName, String password, String email, String money) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", money='" + money + '\'' +
                '}';
    }
}
