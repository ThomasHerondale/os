package bridge;

import java.util.LinkedList;
import java.util.List;

public class Bridge {
    List<Car> vehicles = new LinkedList<>();

    public synchronized void enter(Car car) {
        while (cannotEnter(car)) {
            try {
                System.out.println(car + " cannot enter and is waiting.");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(car + " is entering.");
        vehicles.add(car);
    }

    public synchronized void exit(Car car) {
        System.out.println(car + " is exiting.");
        vehicles.remove(car);
        notifyAll();
    }

    private boolean cannotEnter(Car car) {
        var capacity = 0;
        for (Car vehicle : vehicles) {
            capacity += vehicle.weight;
            if (vehicle.direction != car.direction)
                return true;
        }
        return capacity + car.weight > 5_000;
    }
}
