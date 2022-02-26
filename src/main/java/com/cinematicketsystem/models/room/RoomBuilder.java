package com.cinematicketsystem.models.room;

import com.cinematicketsystem.models.seat.Seat;

import java.util.List;

public final class RoomBuilder {
    private Room room;

    private RoomBuilder() {
        room = new Room();
    }

    public static RoomBuilder aRoom() {
        return new RoomBuilder();
    }

    public RoomBuilder setId(Integer id) {
        room.setId(id);
        return this;
    }

    public RoomBuilder setScreenSize(Integer screenSize) {
        room.setScreenSize(screenSize);
        return this;
    }

    public RoomBuilder setTheaterType(String theaterType) {
        room.setTheaterType(theaterType);
        return this;
    }

    public RoomBuilder setSeatsNumber(Integer seatsNumber) {
        room.setSeatsNumber(seatsNumber);
        return this;
    }

    public RoomBuilder setSeats(List<Seat> seats) {
        room.setSeats(seats);
        return this;
    }

    public Room build() {
        return room;
    }
}
