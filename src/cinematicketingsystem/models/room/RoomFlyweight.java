package cinematicketingsystem.models.room;

import java.util.HashMap;

public class RoomFlyweight {

    private HashMap<Integer,Room> map;

    public RoomFlyweight(){
        map = new HashMap<>();
    }

    public Room getRoom(Integer id, Integer screenSize, String theaterType, Integer seatsNumber){
        if(map.containsKey(id)) return map.get(id);
        map.put(id,new Room(id,screenSize,theaterType,seatsNumber));
        return map.get(id);
    }

}