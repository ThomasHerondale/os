package prison;

import java.util.Objects;

public class Prisoner extends Thread {
    int id;
    PrisonerType type;
    Prison prison;

    public Prisoner(int id, PrisonerType type, Prison prison) {
        super("Prisoner " + id);
        this.id = id;
        this.type = type;
        this.prison = prison;
    }

    @Override
    public void run() {
        prison.startYardTime(this);
        try {
            Thread.sleep((int) (Math.random() * Prison.yard_time));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        prison.endYardTime(this);
    }

    @Override
    public String toString() {
        return getName() + "(" + type + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prisoner prisoner = (Prisoner) o;
        return id == prisoner.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
