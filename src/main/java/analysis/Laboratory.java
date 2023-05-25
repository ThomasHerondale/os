package analysis;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import static analysis.Exam.BLOOD_EXAM;

public class Laboratory {
    Queue<Customer> waitingRoom = new LinkedList<>();
    Map<Analyst, Queue<Customer>> waitingForOkay = new HashMap<>();

    public synchronized void enqueue(Customer customer) {
        System.out.println(customer + " takes a seat");
        waitingRoom.add(customer);
        notifyAll();
    }

    public synchronized void examineCustomer(Analyst analyst) {
        while (waitingRoom.isEmpty()) {
            System.err.println("No customers to examine -> " + analyst + " waiting");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        var examined = waitingRoom.remove();
        try {
            Thread.sleep((int) (Math.random() * 501));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (examined.exam == BLOOD_EXAM) {
            System.out.println(examined + " blood exam finished. " + analyst + " has put it in waiting");
            waitingForOkay.get(analyst).add(examined);
        } else {
            System.out.println(examined + " pays and exits");
        }
    }

    public synchronized void tellToGo(Analyst analyst) {
        var queue = waitingForOkay.get(analyst);
        if (!queue.isEmpty()) {
            if (queue.peek().examsWaited > 2) { // customer goes only if has waited 2 exams
                var exiting = queue.remove();
                System.out.println(analyst + " tells " + exiting + " to go");
            }
            for (var customer : queue)
                customer.examsWaited++;
        }
    }
}
