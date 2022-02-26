package com.cinematicketsystem.models.ticket;

import com.cinematicketsystem.models.movie.Movie;
import com.cinematicketsystem.models.seat.Seat;
import com.cinematicketsystem.models.user.customer.Customer;

import java.sql.Timestamp;

public final class TicketBuilder {
    private Ticket ticket;

    private TicketBuilder() {
        ticket = new Ticket();
    }

    public static TicketBuilder aTicket() {
        return new TicketBuilder();
    }

    public TicketBuilder setId(Integer id) {
        ticket.setId(id);
        return this;
    }

    public TicketBuilder setTimeBooked(Timestamp timeBooked) {
        ticket.setTimeBooked(timeBooked);
        return this;
    }

    public TicketBuilder setCustomer(Customer customer) {
        ticket.setCustomer(customer);
        return this;
    }

    public TicketBuilder setMovie(Movie movie) {
        ticket.setMovie(movie);
        return this;
    }

    public TicketBuilder setSeat(Seat seat) {
        ticket.setSeat(seat);
        return this;
    }

    public Ticket build() {
        return ticket;
    }
}
