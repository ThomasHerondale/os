package luggage;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        var storage = new Storage(10);
        var rng = new Random();

        User[] users = new User[50];
        for (int i = 0; i < users.length; i++) {
            users[i] = new User(i, rng.nextInt(5) + 1, storage);
            users[i].start();
        }
    }
}
