public class MyThread extends Thread {
    Hotel hotel;
    GuestRequest guest;

    MyThread(Hotel hotel, GuestRequest guest) {
        this.hotel = hotel;
        this.guest = guest;
    }

    public void run() {
        boolean checkedIn = false;
        while (!checkedIn) {
            checkedIn = hotel.checkIn(guest);
            if (checkedIn) {
                try {
                    Thread.sleep(guest.getDuration());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                hotel.checkOut(guest);
            } else {
                synchronized (hotel) {
                    try {
                        hotel.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
