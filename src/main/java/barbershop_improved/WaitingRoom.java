package barbershop_improved;

import barbershop.Barber;
import barbershop.Customer;

import java.util.LinkedList;
import java.util.Queue;

public class WaitingRoom extends barbershop.WaitingRoom {
    Queue<Customer> waitingSpace = new LinkedList<>();
    Queue<Customer> payingSpace = new LinkedList<>();

    @Override
    public synchronized void enqueue(Customer customer) {
        if (waitingSpace.size() < 5) {
            System.out.println(customer + " is waiting at the entrance.");
            waitingSpace.add(customer);
        } else
            System.err.println("Barbershop is full. " + customer + " goes away.");

        if (sofa.size() < 5) {
            var nowSeating = waitingSpace.remove();
            sofa.add(nowSeating); // let the next customer waiting seat
            System.out.println(nowSeating + " takes a seat.");
            notifyAll();
        }
    }

    @Override
    public synchronized void serveCustomer(Barber barber) {
        while (sofa.isEmpty()) {
            try {
                System.err.println(barber + " is waiting for clients.");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        var served = sofa.remove();

        if (!waitingSpace.isEmpty()) {
            var nowSeating = waitingSpace.remove();
            sofa.add(nowSeating); // let the next customer waiting seat
            System.out.println(nowSeating + " takes a seat.");
        }

        System.out.println(barber + " is now serving " + served);

        try {
            Thread.sleep((int) (Math.random() * 501));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(barber + " has finished serving " + served);

        payingSpace.add(served);
        System.out.println(served + " is now waiting to pay.");

        var payed = payingSpace.remove();
        System.out.println(barber + " has made " + payed + " pay.");
    }
}
