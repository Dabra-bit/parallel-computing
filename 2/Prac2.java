
public class Prac2 implements Runnable{

    public static void main(String[] args) {
        new Thread(new Prac2(), "Pepe").start();
        new Thread(new Prac2(), "Juan").start();
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