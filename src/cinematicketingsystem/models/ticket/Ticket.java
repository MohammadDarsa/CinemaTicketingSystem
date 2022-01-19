package cinematicketingsystem.models.ticket;

import cinematicketingsystem.annotations.Col;
import cinematicketingsystem.annotations.ID;
import cinematicketingsystem.annotations.Table;

import java.util.Date;
import java.util.Objects;

@Table(name = "ticket")
public class Ticket {
    @ID
    @Col(name  = "id")
    private Integer id;
    @Col(name  = "time_booked", insertIgnore = true, updateIgnore = true)
    private Date timeBooked;
    @Col(name  = "customer_id", insertIgnore = true, updateIgnore = true)
    private Integer customerId;
    @Col(name  = "movie_id", insertIgnore = true, updateIgnore = true)
    private Integer movieId;
    @Col(name  = "seat_id", insertIgnore = true, updateIgnore = true)
    private Integer seatId;

    public Ticket() {
    }

    public Ticket(Date timeBooked, Integer customerId, Integer movieId, Integer seatId) {
        this.timeBooked = timeBooked;
        this.customerId = customerId;
        this.movieId = movieId;
        this.seatId = seatId;
    }

    public Ticket(Integer id, Date timeBooked, Integer customerId, Integer movieId, Integer seatId) {
        this.id = id;
        this.timeBooked = timeBooked;
        this.customerId = customerId;
        this.movieId = movieId;
        this.seatId = seatId;
    }

    public Integer getId() {
        return id;
    }

    public Ticket setId(Integer id) {
        this.id = id;
        return this;
    }

    public Date getTimeBooked() {
        return timeBooked;
    }

    public Ticket setTimeBooked(Date timeBooked) {
        this.timeBooked = timeBooked;
        return this;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public Ticket setCustomerId(Integer customerId) {
        this.customerId = customerId;
        return this;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public Ticket setMovieId(Integer movieId) {
        this.movieId = movieId;
        return this;
    }

    public Integer getSeatId() {
        return seatId;
    }

    public Ticket setSeatId(Integer seatId) {
        this.seatId = seatId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", timeBooked=" + timeBooked +
                ", customerId=" + customerId +
                ", movieId=" + movieId +
                ", seatId=" + seatId +
                '}';
    }
}
