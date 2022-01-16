package cinematicketingsystem.models.room;

import java.util.HashMap;

public class RoomFlyweight {

    private HashMap<String,Room> map;

    public RoomFlyweight(){
        map = new HashMap<String,Room>();
    }

    public Room getRoom(String id, String screenSize, String theaterType, String seatsNumber){
        if(map.containsKey(id)) return map.get(id);
        map.put(id,new Room(id,screenSize,theaterType,seatsNumber));
        return map.get(id);
    }

}
