package cinematicketingsystem.models.seat;

import cinematicketingsystem.annotations.Col;
import cinematicketingsystem.annotations.ID;
import cinematicketingsystem.annotations.Table;

import java.util.Objects;

@Table(name = "seat")
public class Seat {
    @ID
    @Col(name = "id")
    private Integer id;
    @Col(name = "seat_num")
    private Integer seatNumber;
    @Col(name = "row_num")
    private Integer rowNumber;
    @Col(name = "taken")
    private Boolean isTaken;
    @Col(name = "room_id", insertIgnore = true, updateIgnore = true)
    private Integer roomId;

    public Seat() {
    }

    public Seat(Integer seatNumber, Integer rowNumber, Boolean isTaken, Integer roomId) {
        this.seatNumber = seatNumber;
        this.rowNumber = rowNumber;
        this.isTaken = isTaken;
        this.roomId = roomId;
    }

    public Seat(Integer id, Integer seatNumber, Integer rowNumber, Boolean isTaken, Integer roomId) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.rowNumber = rowNumber;
        this.isTaken = isTaken;
        this.roomId = roomId;
    }

    public Integer getId() {
        return id;
    }

    public Seat setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public Seat setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
        return this;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public Seat setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
        return this;
    }

    public Boolean getTaken() {
        return isTaken;
    }

    public Seat setTaken(Boolean taken) {
        isTaken = taken;
        return this;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public Seat setRoomId(Integer roomId) {
        this.roomId = roomId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return Objects.equals(id, seat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", seatNumber=" + seatNumber +
                ", rowNumber=" + rowNumber +
                ", isTaken=" + isTaken +
                ", roomId=" + roomId +
                '}';
    }
}
