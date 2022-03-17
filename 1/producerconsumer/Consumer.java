package producerconsumer;

import producerconsumer.gui.ConsumerCard;

public class Consumer extends Thread {
    public static final int CONSUME_SLEEP = 500;

    private Buffer buffer;
    private ConsumerCard gui;

    public Consumer(String name, Buffer buffer, ConsumerCard gui) {
        super(name);
        this.buffer = buffer;
        this.gui = gui;
        this.gui.setVisible(true);
    }

    public void consume() throws InterruptedException {
        synchronized (this.buffer) {
            while(this.buffer.size() == Buffer.MIN_ELEMENTS) {
                System.out.println(getName() + ": Empty warehouse, waiting on producers...");
                gui.updateCard(ConsumerCard.SLEEPING);
                this.buffer.wait();
            }

            int num = this.buffer.remove();
            gui.updateCard(ConsumerCard.CONSUMING);
            System.out.println(getName() + ": got: " + num);
            this.buffer.notifyAll();
        }
        Thread.sleep(CONSUME_SLEEP);
    }

    @Override
    public void run() {
        while(true) {
            try {
                this.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
