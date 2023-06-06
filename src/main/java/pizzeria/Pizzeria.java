package pizzeria;

import java.util.*;

public class Pizzeria {
    static Random rng = new Random();
    final int tableCount = 10;
    List<Table> tables = new ArrayList<>(tableCount);
    List<CustomerGroup> communityTable = new ArrayList<>(2);

    public Pizzeria() {
        for (int i = 0; i < tableCount; i++) {
            int tableSize = rng.nextInt(1, 4) * 2;
            tables.add(new Table(tableSize));
        }
        tables.sort(Comparator.comparingInt(x -> x.capacity));
    }

    public synchronized void enterPizzeria(CustomerGroup group) {
        int table = findTable(group.size);
        while (table == -1) {
            System.err.println(group + " doesn't find a table and waits.");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            table = findTable(group.size);
        }

        if (table == -2) {
            System.out.println(group + " finds place in the communitary table.");
            communityTable.add(group);
        } else {
            System.out.println(group + " finds place at table " + table);
            tables.get(table).customerGroup = group;
        }
        group.table = table;
    }

    public synchronized void exitPizzeria(CustomerGroup group) {
        if (group.table == -2)
            communityTable.remove(group);
        else {
            tables.get(group.table).customerGroup = null;
        }
        System.out.println(group + " leaves table.");
        notifyAll();
    }

    private int findTable(int size) {
        for (int i = 0; i < tables.size(); i++) {
            var table = tables.get(i);
            if (table.fits(size))
                return i;
        }
        if (fits(communityTable, size))
            return -2;
        return -1;
    }

    private boolean fits(List<CustomerGroup> communityTable, int size) {
        int occupied = 0;
        for (var customerGroup : communityTable) {
            occupied += customerGroup.size;
        }
        return (20 - occupied) >= size;
    }
}
