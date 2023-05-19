package prison;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        var rng = new Random();

        var prison = new Prison();

        Prisoner[] prisoners = new Prisoner[50];
        for (int i = 0; i < prisoners.length; i++) {
            var type = PrisonerType.values()[rng.nextInt(2)];
            prisoners[i] = new Prisoner(i, type, prison);
            prisoners[i].start();

            try {
                Thread.sleep((int) (Math.random() * 201));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
