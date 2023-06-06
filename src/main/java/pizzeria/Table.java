package pizzeria;

import java.util.List;

public class Table {
    int capacity;
    CustomerGroup customerGroup = null;

    public Table(int capacity) {
        this.capacity = capacity;
    }

    public boolean fits(int size) {
        return customerGroup == null && capacity >= size;
    }
}
