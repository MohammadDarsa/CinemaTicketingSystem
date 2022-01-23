package cinematicketingsystem.models.user.admin;

public final class AdminBuilder {
    private Admin admin;

    private AdminBuilder() {
        admin = new Admin();
    }

    public static AdminBuilder anAdmin() {
        return new AdminBuilder();
    }

    public AdminBuilder setId(Integer id) {
        admin.setId(id);
        return this;
    }

    public AdminBuilder setName(String name) {
        admin.setName(name);
        return this;
    }

    public AdminBuilder setAddress(String address) {
        admin.setAddress(address);
        return this;
    }

    public AdminBuilder setPhone(Integer phone) {
        admin.setPhone(phone);
        return this;
    }

    public AdminBuilder setEmail(String email) {
        admin.setEmail(email);
        return this;
    }

    public AdminBuilder setPassword(String password) {
        admin.setPassword(password);
        return this;
    }

    public Admin build() {
        return admin;
    }
}
