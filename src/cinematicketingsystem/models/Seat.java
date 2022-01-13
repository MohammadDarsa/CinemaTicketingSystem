package cinematicketingsystem.models;

import cinematicketingsystem.annotations.Col;
import cinematicketingsystem.annotations.ID;
import cinematicketingsystem.annotations.Table;

import java.util.Objects;

@Table(name = "seat")
public class Seat {
    @ID
    @Col(name = "id")
    private String id;
    @Col(name = "seat_num")
    private String seatNumber;
    @Col(name = "row_num")
    private String rowNumber;
    @Col(name = "taken")
    private String isTaken;
    @Col(name = "room_id", insertIgnore = true, updateIgnore = true)
    private String roomId;

    public Seat() {
    }

    public Seat(String seatNumber, String rowNumber, String isTaken, String roomId) {
        this.seatNumber = seatNumber;
        this.rowNumber = rowNumber;
        this.isTaken = isTaken;
        this.roomId = roomId;
    }

    public Seat(String id, String seatNumber, String rowNumber, String isTaken, String roomId) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.rowNumber = rowNumber;
        this.isTaken = isTaken;
        this.roomId = roomId;
    }

    public String getId() {
        return id;
    }

    public Seat setId(String id) {
        this.id = id;
        return this;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public Seat setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
        return this;
    }

    public String getRowNumber() {
        return rowNumber;
    }

    public Seat setRowNumber(String rowNumber) {
        this.rowNumber = rowNumber;
        return this;
    }

    public String getIsTaken() {
        return isTaken;
    }

    public Seat setIsTaken(String isTaken) {
        this.isTaken = isTaken;
        return this;
    }

    public String getRoomId() {
        return roomId;
    }

    public Seat setRoomId(String roomId) {
        this.roomId = roomId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return id.equals(seat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id='" + id + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                ", rowNumber='" + rowNumber + '\'' +
                ", isTaken='" + isTaken + '\'' +
                ", roomId='" + roomId + '\'' +
                '}';
    }
}
