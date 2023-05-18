package barbershop;

public class Main {
    public static void main(String[] args) {
        var waitingRoom = new WaitingRoom();

        Barber[] barbers = new Barber[3];
        for (int i = 0; i < barbers.length; i++) {
            barbers[i] = new Barber(i, waitingRoom);
            barbers[i].start();
        }

        Customer[] customers = new Customer[50];
        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Customer(i, waitingRoom);
            customers[i].start();

            try {
                Thread.sleep((int) (Math.random() * 501));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
