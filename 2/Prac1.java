
public class Prac1 extends Thread {

    public Prac1(String name) {
        super(name);
    }
    public static void main(String[] args) {
        new Prac1("Pepe").start();
        new Prac1("Juan").start();
        System.out.println("Termina thread main");
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++){
            System.out.println(i + " " + Thread.currentThread().getName());
        }
        System.out.println("Termina el thread " + Thread.currentThread().getName());
    }
}