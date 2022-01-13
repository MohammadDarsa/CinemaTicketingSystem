package cinematicketingsystem.models.user.admin;

import cinematicketingsystem.annotations.Col;
import cinematicketingsystem.annotations.ID;
import cinematicketingsystem.annotations.Table;

import java.util.Objects;

@Table(name = "admin")
public class Admin {
    @ID
    @Col(name  = "id")
    private String id;
    @Col(name  = "name")
    private String name;
    @Col(name  = "address")
    private String address;
    @Col(name  = "phone")
    private String phone;
    @Col(name  = "email")
    private String email;
    @Col(name  = "password")
    private String password;

    public Admin() {
    }

    public Admin(String name, String address, String phone, String email, String password) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public Admin(String id, String name, String address, String phone, String email, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public Admin setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Admin setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Admin setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Admin setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Admin setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Admin setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return id.equals(admin.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
