package bridge;

import java.util.Random;

import static bridge.Direction.*;

public class Main {
    public static void main(String[] args) {
        var rng = new Random();
        var bridge = new Bridge();

        Car[] cars = new Car[10];
        for (int i = 0; i < cars.length; i++) {
            var direction = rng.nextInt(2) == 0 ? DIRECT : INVERSE;
            cars[i] = new Car(i, direction, rng.nextInt(100), bridge);
            cars[i].start();
        }
    }
}
