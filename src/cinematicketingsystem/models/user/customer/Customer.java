package cinematicketingsystem.models.user.customer;

import cinematicketingsystem.annotations.Col;
import cinematicketingsystem.annotations.ID;
import cinematicketingsystem.annotations.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Objects;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer {
    @ID
    @Col(name  = "id")
    @EqualsAndHashCode.Include
    private Integer id;
    @Col(name  = "username")
    private String name;
    @Col(name  = "password")
    private String password;
    @Col(name  = "phone")
    private Integer phone;
    @Col(name  = "email")
    private String email;
    @Col(name  = "balance")
    private Double balance;
    @Col(name  = "address")
    private String address;
    @Col(name = "tickets_bought", updateIgnore = true, insertIgnore = true)
    private Integer ticketsBought;
}
