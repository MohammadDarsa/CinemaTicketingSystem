package cinematicketingsystem.models.user.customer;

public class CustomerBuilder {
    private Customer customer;

    public CustomerBuilder() {
        customer = new Customer();
        customer.setTicketsBought("0");
    }
    public void buildId(String id){
        customer.setId(id);
    }

    public void buildUserName(String userName){
        customer.setName(userName);
    }

    public void buildPassword(String password){
        customer.setPassword(password);
    }

    public void buildMoney(String balance){
        customer.setBalance(balance);
    }

    public void buildEmail(String email){
        customer.setEmail(email);
    }

    public void buildPhone(String phone) {
        customer.setPhone(phone);
    }

    public void buildAddress(String address) {
        customer.setAddress(address);
    }

    public void buildTicketsBought(String ticketsBought) {
        customer.setTicketsBought(ticketsBought);
    }

    public Customer getCustomer(){
        return customer;
    }

}
