package prison;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static prison.PrisonerType.*;

public class Prison {
    List<Prisoner> yard = new ArrayList<>();
    static final long yard_time = 3000;

    public synchronized void startYardTime(Prisoner prisoner) {
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
    }

    public synchronized void endYardTime(Prisoner prisoner) {
        System.out.println(prisoner + " has finished his yard time.");
        yard.remove(prisoner);
        notifyAll();
    }

    private boolean canEnter(Prisoner prisoner) {
        if (prisoner.type == COMMON)
            return true;

        int yakuza_count = 0, mafia_count = 0;
        for (var inYard : yard) {
           if (inYard.type == MAFIA)
               mafia_count++;
           else
               yakuza_count++;
        }
        if (prisoner.type == MAFIA)
            mafia_count++;
        else
            yakuza_count++;

        System.out.println("Y: " + yakuza_count + " - M: " + mafia_count);

        return Math.abs(yakuza_count - mafia_count) < 1;
    }
}
