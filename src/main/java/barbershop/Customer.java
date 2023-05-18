package barbershop;

public class Customer extends Thread {
    int id;
    WaitingRoom waitingRoom;

    public Customer(int id, WaitingRoom waitingRoom) {
        super("Customer " + id);
        this.id = id;
        this.waitingRoom = waitingRoom;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((int) (Math.random() * 501));
            waitingRoom.enqueue(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
