package cinematicketingsystem.models.user.customer;

import cinematicketingsystem.annotations.Col;
import cinematicketingsystem.annotations.ID;
import cinematicketingsystem.annotations.Table;

import java.util.Objects;

@Table(name = "customer")
public class Customer {
    @ID
    @Col(name  = "id")
    private String id;
    @Col(name  = "username")
    private String name;
    @Col(name  = "password")
    private String password;
    @Col(name  = "phone")
    private String phone;
    @Col(name  = "email")
    private String email;
    @Col(name  = "balance")
    private String balance;
    @Col(name  = "address")
    private String address;

    public Customer() {
    }

    public Customer(String name, String password, String phone, String email, String balance, String address) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.balance = balance;
        this.address = address;
    }

    public Customer(String id, String name, String password, String phone, String email, String balance, String address) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.balance = balance;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public Customer setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Customer setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Customer setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Customer setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Customer setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getBalance() {
        return balance;
    }

    public Customer setBalance(String balance) {
        this.balance = balance;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Customer setAddress(String address) {
        this.address = address;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", balance='" + balance + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
