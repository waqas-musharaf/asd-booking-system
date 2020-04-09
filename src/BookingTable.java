import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

public class BookingTable extends Observable {
    private ArrayList<Booking> table = new ArrayList<Booking>();

    public BookingTable() {
        super();
    }

    public ArrayList<Booking> getTable() {
        return table;
    }

    public void addBookingToTable(Booking booking) {
        table.add(booking);

        // fire notifications to any registered Observers
        setChanged();
        notifyObservers();
    }

    public void removeBookingFromTable(Booking booking) {
        table.remove(booking);

        // fire notifications to any registered Observers
        setChanged();
        notifyObservers();
    }

    public Booking getBookingFromTable(int bookingId) {
        List<Booking> returnedBooking = table.stream()
                .filter(Booking -> Booking.getBookingId() == bookingId)
                .collect(Collectors.toList());

        if (returnedBooking.isEmpty()) {
            return null;
            // handle empty list
        } else if (returnedBooking.size() != 1) {
            return null;
            // handle list.size != 1
        } else {
            return returnedBooking.get(0);
        }
    }

    public Boolean checkBookingExists(int roomId, LocalDateTime dateTime) {
        List<Booking> returnedBooking = table.stream()
                .filter(Booking -> Booking.getRoomId() == roomId)
                .filter(Booking -> Booking.getDateTime().isEqual(dateTime))
                .collect(Collectors.toList());

        if (returnedBooking.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
