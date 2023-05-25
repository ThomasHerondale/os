package analysis;

import java.util.LinkedList;

public class Analyst extends Thread {
    int id;
    Laboratory lab;

    public Analyst(int id, Laboratory lab) {
        super("Analyst " + id);
        this.id = id;
        this.lab = lab;
        lab.waitingForOkay.put(this, new LinkedList<>());
    }

    @Override
    public void run() {
        super.run();
        while (true) {
            lab.examineCustomer(this);
            lab.tellToGo(this);
        }

    }

    @Override
    public String toString() {
        return getName();
    }
}
