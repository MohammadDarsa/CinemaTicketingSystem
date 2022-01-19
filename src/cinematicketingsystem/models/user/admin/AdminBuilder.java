package cinematicketingsystem.models.user.admin;

public final class AdminBuilder {
    private Integer id;
    private String name;
    private String address;
    private Integer phone;
    private String email;
    private String password;

    private AdminBuilder() {
    }

    public static AdminBuilder anAdmin() {
        return new AdminBuilder();
    }

    public AdminBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public AdminBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public AdminBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public AdminBuilder setPhone(Integer phone) {
        this.phone = phone;
        return this;
    }

    public AdminBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public AdminBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public Admin build() {
        Admin admin = new Admin();
        admin.setId(id);
        admin.setName(name);
        admin.setAddress(address);
        admin.setPhone(phone);
        admin.setEmail(email);
        admin.setPassword(password);
        return admin;
    }
}
