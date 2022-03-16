package sleepingbarber;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

    private static final int MAX_CLIENTS_WAITING = 3;
    public static void main(String[] args) {
        BlockingQueue<Thread> waitingRoom = new ArrayBlockingQueue<>(MAX_CLIENTS_WAITING);
        Barber barber = new Barber("Barber", waitingRoom);
        barber.start();

        int i = 0;
        while(true) {
            Client client = new Client("Client" + i++, waitingRoom);
            client.start();

            sleep(Client.SPAWN_TIME);
        }
    }

    private static void sleep(int timeMillis) {
        try {
            Thread.sleep(timeMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}