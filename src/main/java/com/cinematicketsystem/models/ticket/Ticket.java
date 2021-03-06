package com.cinematicketsystem.models.ticket;


import com.cinematicketsystem.annotations.*;
import com.cinematicketsystem.models.movie.Movie;
import com.cinematicketsystem.models.seat.Seat;
import com.cinematicketsystem.models.user.customer.Customer;
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
    @Col(name = "customer_id")
    private Integer customerId;
    @Col(name = "movie_id")
    private Integer movieId;
    @Col(name = "seat_id")
    private Integer seatId;

    @OneToOne(key = "customer_id")
    private Customer customer;
    @OneToOne(key = "movie_id")
    private Movie movie;
    @OneToOne(key = "seat_id")
    private Seat seat;
}
