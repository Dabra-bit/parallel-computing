package producerconsumer;

public class Consumer extends Thread {

    private Buffer buffer;
    private int sleep;

    public Consumer(String name, Buffer buffer, int sleep) {
        super(name);
        this.buffer = buffer;
        this.sleep = sleep;
    }

    public void consume() throws InterruptedException {
        synchronized (this.buffer) {
            while(this.buffer.size() == Buffer.MIN_ELEMENTS) {
                System.out.println(getName() + ": Empty warehouse, waiting on producers...");
                this.buffer.wait();
            }

            int num = this.buffer.remove();
            System.out.println(getName() + ": got: " + num);
            this.buffer.notifyAll();
        }
        Thread.sleep(this.sleep);
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
