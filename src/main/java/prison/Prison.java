package prison;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Prison {
    List<Prisoner> yard = new ArrayList<>();
    static final long yard_time = 3000;

    public synchronized void yardTime(Prisoner prisoner) {
        System.out.println(prisoner + " tries to enter.");
        while (!canEnter(prisoner)) {
            try {
                System.err.println(prisoner + " cannot enter yard and is waiting.");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(prisoner + " is entering yard.");
        yard.add(prisoner);

        try {
            Thread.sleep(yard_time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(prisoner + " has finished his yard time.");
        yard.remove(prisoner);
        notifyAll();
    }

    private boolean canEnter(Prisoner prisoner) {
        Integer[] clanCount = new Integer[3];
        Arrays.fill(clanCount, 0);
        for (var inYard : yard) {
            clanCount[inYard.type.ordinal()]++;
        }
        var futureCount = clanCount[prisoner.type.ordinal()] + 1;
        for (int i = 1; i < clanCount.length; i++) {
            if (Math.abs(clanCount[i] - futureCount) > 1)
                return false;
        }
        return true;
    }
}
