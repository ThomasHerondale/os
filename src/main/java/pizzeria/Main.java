package pizzeria;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        CustomerGroup[] groups = new CustomerGroup[40];
        var pizzeria = new Pizzeria();
        for (int i = 0; i < groups.length; i++) {
            groups[i] = new CustomerGroup(i, pizzeria);
            groups[i].start();
        }
    }
}
