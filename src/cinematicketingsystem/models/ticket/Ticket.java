package cinematicketingsystem.models.ticket;

import cinematicketingsystem.annotations.Col;
import cinematicketingsystem.annotations.ID;
import cinematicketingsystem.annotations.Table;

import java.util.Objects;

@Table(name = "ticket")
public class Ticket {
    @ID
    @Col(name  = "id")
    private String id;
    @Col(name  = "time_booked", insertIgnore = true, updateIgnore = true)
    private String timeBooked;
    @Col(name  = "customer_id", insertIgnore = true, updateIgnore = true)
    private String customerId;
    @Col(name  = "movie_id", insertIgnore = true, updateIgnore = true)
    private String movieId;
    @Col(name  = "seat_id", insertIgnore = true, updateIgnore = true)
    private String seatId;

    public Ticket() {
    }

    public Ticket(String timeBooked, String customerId, String movieId, String seatId) {
        this.timeBooked = timeBooked;
        this.customerId = customerId;
        this.movieId = movieId;
        this.seatId = seatId;
    }

    public Ticket(String id, String timeBooked, String customerId, String movieId, String seatId) {
        this.id = id;
        this.timeBooked = timeBooked;
        this.customerId = customerId;
        this.movieId = movieId;
        this.seatId = seatId;
    }

    public Ticket setId(String id) {
        this.id = id;
        return this;
    }

    public Ticket setTimeBooked(String timeBooked) {
        this.timeBooked = timeBooked;
        return this;
    }

    public Ticket setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public Ticket setMovieId(String movieId) {
        this.movieId = movieId;
        return this;
    }

    public Ticket setSeatId(String seatId) {
        this.seatId = seatId;
        return this;
    }

    public String getId() {
        return id;
    }

    public String getTimeBooked() {
        return timeBooked;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getSeatId() {
        return seatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id.equals(ticket.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", timeBooked='" + timeBooked + '\'' +
                ", customerId='" + customerId + '\'' +
                ", movieId='" + movieId + '\'' +
                ", seatId='" + seatId + '\'' +
                '}';
    }
}
