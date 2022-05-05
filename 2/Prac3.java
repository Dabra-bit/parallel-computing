public class Prac3 implements Runnable {

    private boolean wait;
    private Runnable waiter;

    public Prac3(boolean wait, Runnable waiter) {
        this.wait = wait;
        this.waiter = waiter;
    }

    public static void main(String[] args) {
        Runnable rPepe = new Prac3(true, null);
        Thread tPepe = new Thread(rPepe, "Pepe");
        Thread tJuan = new Thread(new Prac3(false, rPepe), "Juan");

        tJuan.setPriority(Thread.MIN_PRIORITY);
        tPepe.setPriority(Thread.MAX_PRIORITY);

        tJuan.start();
        tPepe.start();

        System.out.println("Termina thread main");
    }

    public void setWait(boolean wait) {
        this.wait = wait;
    }

    @Override
    public void run() {
        while(wait) {
            Thread.yield();
        }
        for(int i = 0; i < 10; i++){
            System.out.println(i + " " + Thread.currentThread().getName());
        }
        System.out.println("Termina el thread " + Thread.currentThread().getName());
        if(waiter != null) {
            ((Prac3) waiter).setWait(false);
        }
    }
}