import java.util.ArrayList;
import java.util.List;

public class Hotel{
    private int capacity;
    private List<GuestRequest> guests;

    public Hotel(int capacity) {
        this.capacity = capacity;
        this.guests = new ArrayList<>();
    }

    public synchronized boolean checkIn(GuestRequest guest) {
        if (guests.size() < capacity) {
            guests.add(guest);
            System.out.println("Guest " + guest.getName() + " checked in");
            System.out.println("Remaining capacity: " + (capacity - guests.size()) + "\n");
            return true;
        } else {
            System.out.println("No vacancy, guest " + guest.getName() + " has to wait\n");
            return false;
        }
    }

    public synchronized void checkOut(GuestRequest guest) {
        guests.remove(guest);
        System.out.println("Guest " + guest.getName() + " checked out, after staying for " + guest.getDuration() + "ms");
        System.out.println("Remaining capacity: " + (capacity - guests.size()) + "\n");
        notifyAll();
    }

    public synchronized GuestRequest findGuest(String name) {
        for (GuestRequest guest : guests) {
            if (guest.getName().equals(name)) {
                return guest;
            }
        }
        return null;
    }
}
