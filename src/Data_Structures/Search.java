package Data_Structures;

import javafx.util.Pair;
import java.time.LocalDate;

public class Search {
    public String id;
    public String name;
    public Pair<Integer, String> size;
    public int type;
    public int dAvailability;
    public Pair<LocalDate, Integer> sAvailability;
    public String status;

    // No argument constructor
    public Search() {
    }

    public Search(String id, String name, Pair<Integer, String> size, int type, int dAvailability, Pair<LocalDate, Integer> sAvailability, String status) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.type = type;
        this.dAvailability = dAvailability;
        this.sAvailability = sAvailability;
        this.status = status;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public Pair<Integer, String> getSize() {
        return size;
    }

    public int getType() {
        return type;
    }

    public int getDAvailability() {
        return dAvailability;
    }

    public Pair<LocalDate, Integer> getSAvailability() {
        return sAvailability;
    }

    public String getStatus() {
        return status;
    }
}
