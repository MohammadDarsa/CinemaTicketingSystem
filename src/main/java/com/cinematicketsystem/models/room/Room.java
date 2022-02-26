package com.cinematicketsystem.models.room;

import com.cinematicketsystem.annotations.Col;
import com.cinematicketsystem.annotations.ID;
import com.cinematicketsystem.annotations.OneToMany;
import com.cinematicketsystem.annotations.Table;
import com.cinematicketsystem.models.seat.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "room")
public class Room {
    @ID
    @Col(name = "id")
    @EqualsAndHashCode.Include
    private Integer id;
    @Col(name = "screen_size")
    private Integer screenSize;
    @Col(name = "theater_type")
    private String theaterType;
    @Col(name = "number_seats")
    private Integer seatsNumber;
    @OneToMany(key = "room_id")
    private List<Seat> seats;
}
