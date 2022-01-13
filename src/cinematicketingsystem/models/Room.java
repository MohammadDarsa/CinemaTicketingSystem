package cinematicketingsystem.models;

import cinematicketingsystem.annotations.Col;
import cinematicketingsystem.annotations.ID;
import cinematicketingsystem.annotations.Table;

import java.util.Objects;

@Table(name = "room")
public class Room {
    @ID
    @Col(name = "id")
    private String id;
    @Col(name = "screen_size")
    private String screenSize;
    @Col(name = "theater_type")
    private String theaterType;
    @Col(name = "number_seats")
    private String seatsNumber;

    public Room() {
    }

    public Room(String screenSize, String theaterType, String seatsNumber) {
        this.screenSize = screenSize;
        this.theaterType = theaterType;
        this.seatsNumber = seatsNumber;
    }

    public Room(String id, String screenSize, String theaterType, String seatsNumber) {
        this.id = id;
        this.screenSize = screenSize;
        this.theaterType = theaterType;
        this.seatsNumber = seatsNumber;
    }

    public String getId() {
        return id;
    }

    public Room setId(String id) {
        this.id = id;
        return this;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public Room setScreenSize(String screenSize) {
        this.screenSize = screenSize;
        return this;
    }

    public String getTheaterType() {
        return theaterType;
    }

    public Room setTheaterType(String theaterType) {
        this.theaterType = theaterType;
        return this;
    }

    public String getSeatsNumber() {
        return seatsNumber;
    }

    public Room setSeatsNumber(String seatsNumber) {
        this.seatsNumber = seatsNumber;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id.equals(room.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", screenSize='" + screenSize + '\'' +
                ", theaterType='" + theaterType + '\'' +
                ", seatsNumber='" + seatsNumber + '\'' +
                '}';
    }
}
