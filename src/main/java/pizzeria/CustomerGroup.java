package pizzeria;

import java.util.Objects;
import java.util.Random;

public class CustomerGroup extends Thread {
    int id;
    int size;
    Pizzeria pizzeria;
    int table = -1;
    static Random rng = new Random();

    public CustomerGroup(int id, Pizzeria pizzeria) {
        super("Group " + id);
        this.id = id;
        this.pizzeria = pizzeria;
        size = rng.nextInt(1, 11);
    }

    @Override
    public void run() {
        super.run();
        pizzeria.enterPizzeria(this);
        try {
            Thread.sleep((int) (Math.random() * 3001));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this + " orders.");
        try {
            Thread.sleep((int) (Math.random() * 6001));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pizzeria.exitPizzeria(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerGroup that = (CustomerGroup) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return getName() + "(" + size + ")";
    }
}
