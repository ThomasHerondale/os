package barbershop;

public class Barber extends Thread {
    int id;
    WaitingRoom waitingRoom;

    public Barber(int id, WaitingRoom waitingRoom) {
        super("Barber " + id);
        this.id = id;
        this.waitingRoom = waitingRoom;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep((int) (Math.random() * 501));
                waitingRoom.serveCustomer(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
