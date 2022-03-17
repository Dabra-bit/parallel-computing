package producerconsumer;

import java.util.Random;
import producerconsumer.gui.ProducerCard;

public class Producer extends Thread {
    public static final int PRODUCE_SLEEP = 500;
    private static final Random rand = new Random();

    private Buffer buffer;
    private ProducerCard gui;

    public Producer(String name, Buffer buffer, ProducerCard gui) {
        super(name);
        this.buffer = buffer;
        this.gui = gui;
        this.gui.setVisible(true);
    }

    private void produce(int num) throws InterruptedException {
        synchronized (this.buffer) {
            while(this.buffer.size() == Buffer.MAX_ELEMENTS) {
                System.out.println(getName() + ": Full warehouse, waiting on customers...");
                gui.updateCard(ProducerCard.SLEEPING);
                this.buffer.wait();
            }

            this.buffer.add(num);
            System.out.println(getName() + ": added: " + num);
            gui.updateCard(ProducerCard.PRODUCING);
            this.buffer.notifyAll();
        }
        Thread.sleep(Producer.PRODUCE_SLEEP);
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
