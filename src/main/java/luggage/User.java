package luggage;

import java.util.Objects;

public class User extends Thread {
    int id;
    int luggageCount;
    Storage storage;
    int room = -1;

    public User(int id, int luggageCount, Storage storage) {
        super("User " + id);
        this.id = id;
        this.luggageCount = luggageCount;
        this.storage = storage;
    }

    @Override
    public void run() {
        super.run();
        storage.insertLuggage(this);
        try {
            Thread.sleep((int) (Math.random() * 3001));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        storage.retrieveLuggage(this);
    }

    @Override
    public String toString() {
        return getName() + "(" + luggageCount + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

