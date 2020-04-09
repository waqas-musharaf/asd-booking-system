import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

public class RoomTable extends Observable {
    private ArrayList<Room> table = new ArrayList<Room>();

    public RoomTable() {
        super();
    }

    public ArrayList<Room> getTable() {
        return table;
    }

    public void addRoomToTable(Room room) {
        table.add(room);

        // fire notifications to any registered Observers
        setChanged();
        notifyObservers();
    }

    public void removeRoomFromTable(Room room) {
        table.remove(room);

        // fire notifications to any registered Observers
        setChanged();
        notifyObservers();
    }

    public Room getRoomFromTable(int id) {
        List<Room> returnedRooms = table.stream()
                .filter(Room -> Room.getRoomId() == id)
                .collect(Collectors.toList());

        if (returnedRooms.isEmpty()) {
            return null;
            // handle empty list
        } else if (returnedRooms.size() != 1) {
            return  null;
            // handle list.size != 1
        } else {
            return returnedRooms.get(0);
        }
    }

    public Room getRoomFromTable(String name) {
        List<Room> returnedRooms = table.stream()
                .filter(Room -> Room.getRoomName().equals(name))
                .collect(Collectors.toList());

        if (returnedRooms.isEmpty()) {
            return null;
            // handle empty list
        } else if (returnedRooms.size() != 1) {
            return  null;
            // handle list.size != 1
        } else {
            return returnedRooms.get(0);
        }
    }
}