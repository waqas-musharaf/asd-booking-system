package Data_Structures;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observable;

public class TermTable extends Observable {
    public ArrayList<LocalDate> table = new ArrayList<LocalDate>();

    public TermTable() {
        super();
    }

    public ArrayList<LocalDate> getTable() {
        return table;
    }

    public void addDate(LocalDate date) {
        table.add(date);

        // fire notifications to any registered Observers
        setChanged();
        notifyObservers();
    }

    public void removeDate(LocalDate date) {
        if (table.contains(date)) {
            table.remove(date);
        }

        // fire notifications to any registered Observers
        setChanged();
        notifyObservers();
    }

    public Boolean hasDate (LocalDate date) {
        if (table.contains(date)) {
            return true;
        } else {
            return false;
        }
    }
}