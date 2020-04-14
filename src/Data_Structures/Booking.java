package Data_Structures;

import java.time.LocalDateTime;

public class Booking {
    public int bookingId;
    public int roomId;
    public LocalDateTime dateTime;
    public String name;
    public String contact;
    public String notes;

    // No argument constructor
    public Booking() {
    }

    public Booking(int bookingId, int roomId, LocalDateTime dateTime, String name, String contact, String notes) {
        this.bookingId = bookingId;
        this.roomId = roomId;
        this.dateTime = dateTime;
        this.name = name;
        this.contact = contact;
        this.notes = notes;
    }

    public int getBookingId() { return this.bookingId; }

    public int getRoomId() { return this.roomId; }

    public LocalDateTime getDateTime() { return this.dateTime; }

    public String getName() { return this.name; }

    public String getContact() { return this.contact; }

    public String getNotes() { return this.notes; }
}
