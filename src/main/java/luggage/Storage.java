package luggage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Storage {
    static final int roomCapacity = 10;
    final int roomCount;
    List<Set<User>> rooms;

    public Storage(int roomCount) {
        this.roomCount = roomCount;
        this.rooms = new ArrayList<>(roomCount);
        for (var i = 0; i < roomCount; i++) {
            rooms.add(new HashSet<>());
        }
    }

    public synchronized void insertLuggage(User user) {
        int freeRoom = findSpace(user.luggageCount);
        while (freeRoom == -1) {
            System.err.println(user + " doesn't find space and waits.");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            freeRoom = findSpace(user.luggageCount);
        }

        System.out.println(user + " finds space in room " + freeRoom);
        user.room = freeRoom;
        rooms.get(freeRoom).add(user);
    }

    public synchronized void retrieveLuggage(User user) {
        System.out.println(user + " retrieves its luggage at room " + user.room);
        rooms.get(user.room).remove(user);
        user.room = -1;
        notifyAll();
    }

    private int findSpace(int luggageCount) {
        for (var i = 0; i < roomCount; i++) {
            if (hasSpace(luggageCount, i))
                return i;
        }
        return -1;
    }

    private boolean hasSpace(int luggageCount, int roomNumber) {
        return getCapacity(roomNumber) >= luggageCount;
    }

    private int getCapacity(int roomNumber) {
        int usedSpace = 0;
        for (var user : rooms.get(roomNumber)) {
            usedSpace += user.luggageCount;
        }
        return roomCapacity - usedSpace;
    }
}
