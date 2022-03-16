package producerconsumer;

public class Buffer {
    // Constants
    public static final int MAX_ELEMENTS = 2;
    public static final int MIN_ELEMENTS = 0;
    
    // Properties
    private int[] elements;
    private int counter;

    public Buffer() {
        this.elements = new int[MAX_ELEMENTS];
        this.counter = 0;
    }

    public void add(int el) {
        this.elements[this.counter++] = el;
    }

    public int remove() {
        return this.elements[--this.counter];
    }

    public int size() {
        return this.counter;
    }
}
