package com.cinematicketsystem.models.seat;

import com.cinematicketsystem.annotations.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "seat")
public class Seat {
    @ID
    @Col(name = "id")
    @EqualsAndHashCode.Include
    private Integer id;
    @Col(name = "seat_num")
    private Integer seatNumber;
    @Col(name = "row_num")
    private Integer rowNumber;
    @Col(name = "room_id")
    private Integer roomId;
    @Col(name = "taken")
    private Integer taken;
}
