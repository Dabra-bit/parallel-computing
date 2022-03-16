package sleepingbarber;

import java.util.concurrent.BlockingQueue;

public class Client extends Thread {
    public static final int SPAWN_TIME = 50;

    private BlockingQueue<Thread> waitingRoom;
    private boolean isWaiting;

    public Client(String tName, BlockingQueue<Thread> waitingRoom) {
        super(tName);
        this.waitingRoom = waitingRoom;
        this.isWaiting = true;
    }

    private boolean isInWaitingRoom() {
        return waitingRoom.contains(this);
    }

    private void joinWaitingRoom() throws InterruptedException{
        synchronized (this) {
            boolean clientJoinedWaitingRoom = waitingRoom.offer(this);

            if(!clientJoinedWaitingRoom) {
                System.out.println("Barber shop is full, I'm going home...");
                interrupt();
            }

            while(isInWaitingRoom()) {
                System.out.println(getName() + ": waiting for haircut...");
                wait();
            }
        }
    }

    @Override
    public void run() {
        try {
            joinWaitingRoom();
        } catch (InterruptedException e) {
            e.printStackTrace();
            interrupt();
        }
    }
}
