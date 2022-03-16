package producerconsumer;

import java.util.Random;

public class Producer extends Thread {
    private static final Random rand = new Random();

    private Buffer buffer;
    private int sleep;

    public Producer(String name, Buffer buffer, int sleep) {
        super(name);
        this.buffer = buffer;
        this.sleep = sleep;
    }

    private void produce(int num) throws InterruptedException {
        synchronized (this.buffer) {
            while(this.buffer.size() == Buffer.MAX_ELEMENTS) {
                System.out.println(getName() + ": Full warehouse, waiting on customers...");
                this.buffer.wait();
            }

            this.buffer.add(num);
            System.out.println(getName() + ": added: " + num);
            this.buffer.notifyAll();
        }
        Thread.sleep(this.sleep);
    }

    @Override
    public void run() {
        while(true) {
            int randNum = rand.nextInt(101);

            try {
                this.produce(randNum);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
