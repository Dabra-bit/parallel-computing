package producerconsumer;

public class Main {
    public static final int DEFAULT_SLEEP = 50;
    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Thread producer = new Producer("Productor 1", buffer, DEFAULT_SLEEP);
        Thread consumer = new Consumer("Consumidor 1", buffer, DEFAULT_SLEEP);
        Thread producer2 = new Producer("Productor 2", buffer, DEFAULT_SLEEP);
        Thread consumer2 = new Consumer("Consumidor 2", buffer, DEFAULT_SLEEP);
        
        producer.start();
        consumer.start();
        producer2.start();
        consumer2.start();
    }
}