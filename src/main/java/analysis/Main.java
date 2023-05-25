package analysis;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        var lab = new Laboratory();
        var rng = new Random();

        Customer[] customers = new Customer[30];
        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Customer(i, Exam.values()[rng.nextInt(2)], lab);
            customers[i].start();
        }

        Analyst[] analysts = new Analyst[5];
        for (int i = 0; i < analysts.length; i++) {
            analysts[i] = new Analyst(i, lab);
            analysts[i].start();
        }
    }
}
