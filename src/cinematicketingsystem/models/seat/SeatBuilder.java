package cinematicketingsystem.models.seat;

public final class SeatBuilder {
    private Seat seat;

    private SeatBuilder() {
        seat = new Seat();
    }

    public static SeatBuilder aSeat() {
        return new SeatBuilder();
    }

    public SeatBuilder setId(Integer id) {
        seat.setId(id);
        return this;
    }

    public SeatBuilder setSeatNumber(Integer seatNumber) {
        seat.setSeatNumber(seatNumber);
        return this;
    }

    public SeatBuilder setRowNumber(Integer rowNumber) {
        seat.setRowNumber(rowNumber);
        return this;
    }

    public SeatBuilder setRoomId(Integer roomId) {
        seat.setRoomId(roomId);
        return this;
    }

    public Seat build() {
        return seat;
    }
}
