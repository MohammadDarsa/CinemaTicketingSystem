package cinematicketingsystem.models.ticket;

import java.util.Date;

public final class TicketBuilder {
    private Integer id;
    private Date timeBooked;
    private Integer customerId;
    private Integer movieId;
    private Integer seatId;

    private TicketBuilder() {
    }

    public static TicketBuilder aTicket() {
        return new TicketBuilder();
    }

    public TicketBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public TicketBuilder setTimeBooked(Date timeBooked) {
        this.timeBooked = timeBooked;
        return this;
    }

    public TicketBuilder setCustomerId(Integer customerId) {
        this.customerId = customerId;
        return this;
    }

    public TicketBuilder setMovieId(Integer movieId) {
        this.movieId = movieId;
        return this;
    }

    public TicketBuilder setSeatId(Integer seatId) {
        this.seatId = seatId;
        return this;
    }

    public Ticket build() {
        Ticket ticket = new Ticket();
        ticket.setId(id);
        ticket.setTimeBooked(timeBooked);
        ticket.setCustomerId(customerId);
        ticket.setMovieId(movieId);
        ticket.setSeatId(seatId);
        return ticket;
    }
}
