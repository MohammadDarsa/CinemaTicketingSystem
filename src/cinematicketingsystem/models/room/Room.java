package cinematicketingsystem.models.room;

import cinematicketingsystem.annotations.Col;
import cinematicketingsystem.annotations.ID;
import cinematicketingsystem.annotations.OneToMany;
import cinematicketingsystem.annotations.Table;
import cinematicketingsystem.models.seat.Seat;

import java.util.List;
import java.util.Objects;

@Table(name = "room")
public class Room {
    @ID
    @Col(name = "id")
    private Integer id;
    @Col(name = "screen_size")
    private Integer screenSize;
    @Col(name = "theater_type")
    private String theaterType;
    @Col(name = "number_seats")
    private Integer seatsNumber;
    @OneToMany(key = "room_id")
    private List<Seat> seats;

    public Room() {
    }

    public Room(Integer id, Integer screenSize, String theaterType, Integer seatsNumber, List<Seat> seats) {
        this.id = id;
        this.screenSize = screenSize;
        this.theaterType = theaterType;
        this.seatsNumber = seatsNumber;
        this.seats = seats;
    }

    public Room(Integer screenSize, String theaterType, Integer seatsNumber, List<Seat> seats) {
        this.screenSize = screenSize;
        this.theaterType = theaterType;
        this.seatsNumber = seatsNumber;
        this.seats = seats;
    }

    public Integer getId() {
        return id;
    }

    public Room setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getScreenSize() {
        return screenSize;
    }

    public Room setScreenSize(Integer screenSize) {
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

    public Integer getSeatsNumber() {
        return seatsNumber;
    }

    public Room setSeatsNumber(Integer seatsNumber) {
        this.seatsNumber = seatsNumber;
        return this;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public Room setSeats(List<Seat> seats) {
        this.seats = seats;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", screenSize=" + screenSize +
                ", theaterType='" + theaterType + '\'' +
                ", seatsNumber=" + seatsNumber +
                ", seats=" + seats +
                '}';
    }
}
