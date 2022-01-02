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
        this.id = "";
        this.userName = "";
        this.password = "";
        this.email = "";
        this.money = "";
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
