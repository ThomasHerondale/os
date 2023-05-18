package barbershop;

import java.util.LinkedList;
import java.util.Queue;

public class WaitingRoom {
    public Queue<Customer> sofa = new LinkedList<>();

    public synchronized void enqueue(Customer customer) {
        if (sofa.size() < 5) {
            System.out.println(customer + " takes a seat.");
            sofa.add(customer);
            notifyAll();
        } else {
            System.err.println("Sofa is full. " + customer + " has gone away.");
        }
    }

    public synchronized void serveCustomer(Barber barber) {
        while (sofa.isEmpty()) {
            try {
                System.err.println(barber + " is waiting for customers.");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        var served = sofa.remove();
        System.out.println(barber + " is now serving " + served);

        try {
            Thread.sleep((int) (Math.random() * 501));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(barber + " has finished serving " + served);
    }
}
