package cinematicketingsystem.models.user.customer;

public final class CustomerBuilder {
    private Customer customer;

    private CustomerBuilder() {
        customer = new Customer();
    }

    public static CustomerBuilder aCustomer() {
        return new CustomerBuilder();
    }

    public CustomerBuilder setId(Integer id) {
        customer.setId(id);
        return this;
    }

    public CustomerBuilder setName(String name) {
        customer.setName(name);
        return this;
    }

    public CustomerBuilder setPassword(String password) {
        customer.setPassword(password);
        return this;
    }

    public CustomerBuilder setPhone(Integer phone) {
        customer.setPhone(phone);
        return this;
    }

    public CustomerBuilder setEmail(String email) {
        customer.setEmail(email);
        return this;
    }

    public CustomerBuilder setBalance(Double balance) {
        customer.setBalance(balance);
        return this;
    }

    public CustomerBuilder setAddress(String address) {
        customer.setAddress(address);
        return this;
    }

    public CustomerBuilder setTicketsBought(Integer ticketsBought) {
        customer.setTicketsBought(ticketsBought);
        return this;
    }

    public Customer build() {
        return customer;
    }
}
