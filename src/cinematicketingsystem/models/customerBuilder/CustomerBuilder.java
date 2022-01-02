package cinematicketingsystem.models.customerBuilder;

import cinematicketingsystem.models.Customer;

public class CustomerBuilder {
    public Customer customer;

    public CustomerBuilder(){
        this.customer = new Customer();
    }

    public void buildId(String id){
        customer.setId(id);
    }

    public void buildUserName(String userName){
        customer.setUserName(userName);
    }

    public void buildPassword(String password){
        customer.setPassword(password);
    }

    public void buildMoney(String money){
        customer.setMoney(money);
    }

    public void buildEmail(String email){
        customer.setEmail(email);
    }

    public Customer getCustomer(){
        return customer;
    }
}
