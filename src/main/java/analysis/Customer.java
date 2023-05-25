package analysis;

public class Customer extends Thread {
    int id;
    Exam exam;
    Laboratory lab;
    int examsWaited;

    public Customer(int id, Exam exam, Laboratory lab) {
        super("Customer " + id);
        this.id = id;
        this.exam = exam;
        this.lab = lab;
        this.examsWaited = 0;
    }

    @Override
    public void run() {
        super.run();
        lab.enqueue(this);
    }

    @Override
    public String toString() {
        return getName();
    }
}
