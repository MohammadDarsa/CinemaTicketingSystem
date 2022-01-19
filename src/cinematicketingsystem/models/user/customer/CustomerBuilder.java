package cinematicketingsystem.models.user.customer;

public final class CustomerBuilder {
    private Integer id;
    private String name;
    private String password;
    private Integer phone;
    private String email;
    private Double balance;
    private String address;
    private Integer ticketsBought;

    private CustomerBuilder() {
    }

    public static CustomerBuilder aCustomer() {
        return new CustomerBuilder();
    }

    public CustomerBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public CustomerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public CustomerBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public CustomerBuilder setPhone(Integer phone) {
        this.phone = phone;
        return this;
    }

    public CustomerBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerBuilder setBalance(Double balance) {
        this.balance = balance;
        return this;
    }

    public CustomerBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public CustomerBuilder setTicketsBought(Integer ticketsBought) {
        this.ticketsBought = ticketsBought;
        return this;
    }

    public Customer build() {
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName(name);
        customer.setPassword(password);
        customer.setPhone(phone);
        customer.setEmail(email);
        customer.setBalance(balance);
        customer.setAddress(address);
        customer.setTicketsBought(ticketsBought);
        return customer;
    }
}
