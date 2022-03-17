package sleepingbarber;

import java.util.concurrent.BlockingQueue;

public class Barber extends Thread{
    public static final int HAIRCUT_TIME = 2000;

    private BlockingQueue<Thread> clientQueue;

    public Barber(String tName, BlockingQueue<Thread> waitingRoom) {
        super(tName);
        this.clientQueue = waitingRoom;
    }

    private void cutHair() {
        Thread client = clientQueue.poll();

        if(client == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            client = clientQueue.take();
        } catch (InterruptedException e) {
            System.out.println("Barber: I'm taking a nap... (...zzz...)");
            e.printStackTrace();
        }

        System.out.println(client.getName() + " is getting a hair cut...");
    }

    private void sleep(int timeMillis) {
        try {
            Thread.sleep(timeMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true) {
            cutHair();
        }
    }
}
