package bridge;

public class Car extends Thread {
    int id;
    Direction direction;
    int weight;

    Bridge bridge;

    public Car(int id, Direction direction, int weight, Bridge bridge) {
        super("Car " + id);
        this.id = id;
        this.direction = direction;
        this.weight = weight;
        this.bridge = bridge;
    }

    @Override
    public void run() {
        super.run();
        bridge.enter(this);
        try {
            Thread.sleep((int) (Math.random() * 10001));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bridge.exit(this);
    }

    @Override
    public String toString() {
        return getName() + "(" + direction + ")";
    }
}

