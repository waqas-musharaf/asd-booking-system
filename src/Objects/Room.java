package Objects;

import java.time.LocalDate;
import java.util.List;

public class Room {
    public int roomId;
    public String roomName;
    public int size;
    public String type;
    public String availability;
    public String status;
    public List<LocalDate> unavailability;

    // No argument constructor
    public Room() {
    }

    public Room(int roomId, String roomName, int size, String type, String availability, String status, List<LocalDate> unavailability) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.size = size;
        this.type = type;
        this.availability = availability;
        this.status = status;
        this.unavailability = unavailability;
    }

    public int getRoomId() {
        return this.roomId;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public int getSize() {
        return this.size;
    }

    public String getType() {
        return this.type;
    }

    public String getAvailability() {
        return this.availability;
    }

    public String getStatus() {
        return this.status;
    }

    public List<LocalDate> getUnavailability() { return this.unavailability; }
}
