package prac4;
public class Main {
    public static void main(String[] args) {
        Contenedor c = new Contenedor();
        Productor produce = new Productor(c);
        Consumidor consume = new Consumidor(c);

        produce.start();
        consume.start();
    }
}