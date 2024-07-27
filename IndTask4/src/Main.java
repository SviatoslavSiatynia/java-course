public class Main {
    public static void main(String[] args) {

        Hotel hotel = new Hotel(2);

        GuestRequest dimaRequest = new GuestRequest("Dima", 4000);
        MyThread dimaThread = new MyThread(hotel, dimaRequest);

        GuestRequest janaRequest = new GuestRequest("Jana", 6000);
        MyThread janaThread = new MyThread(hotel, janaRequest);

        GuestRequest viktorRequest = new GuestRequest("Viktor", 4000);
        MyThread viktorThread = new MyThread(hotel, viktorRequest);

        GuestRequest olegRequest = new GuestRequest("Oleg", 3000);
        MyThread olegThread = new MyThread(hotel, olegRequest);


        dimaThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        janaThread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        viktorThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        olegThread.start();
    }
}