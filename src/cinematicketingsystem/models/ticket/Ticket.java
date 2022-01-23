package cinematicketingsystem.models.ticket;

import cinematicketingsystem.annotations.Col;
import cinematicketingsystem.annotations.ID;
import cinematicketingsystem.annotations.OneToOne;
import cinematicketingsystem.annotations.Table;
import cinematicketingsystem.models.movie.Movie;
import cinematicketingsystem.models.seat.Seat;
import cinematicketingsystem.models.user.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ticket")
public class Ticket {
    @ID
    @Col(name  = "id")
    @EqualsAndHashCode.Include
    private Integer id;
    @Col(name  = "time_booked")
    private Timestamp timeBooked;
    @OneToOne(key = "customer_id")
    private Customer customer;
    @OneToOne(key = "movie_id")
    private Movie movie;
    @OneToOne(key = "seat_id")
    private Seat seat;
}
