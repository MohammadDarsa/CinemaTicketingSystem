package cinematicketingsystem.models.room;

public final class RoomBuilder {

    private Integer id;
    private Integer screenSize;
    private String theaterType;
    private Integer seatsNumber;

    private RoomBuilder() {
    }

    public static RoomBuilder aRoom() {
        return new RoomBuilder();
    }

    public RoomBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public RoomBuilder setScreenSize(Integer screenSize) {
        this.screenSize = screenSize;
        return this;
    }

    public RoomBuilder setTheaterType(String theaterType) {
        this.theaterType = theaterType;
        return this;
    }

    public RoomBuilder setSeatsNumber(Integer seatsNumber) {
        this.seatsNumber = seatsNumber;
        return this;
    }

    public Room build() {
        Room room = new Room();
        room.setId(id);
        room.setScreenSize(screenSize);
        room.setTheaterType(theaterType);
        room.setSeatsNumber(seatsNumber);
        return room;
    }
}
