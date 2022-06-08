package prac5;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        final int N = 100000000;
        final int upperbound = 1000;

        int[] numbers = new int[N];
        Random rand = new Random(); //instance of random class

        for(int i = 0; i < N; i++) {
            int tmpRnd = rand.nextInt(upperbound);
            numbers[i] = tmpRnd;
        }
        
        System.out.println("Calculating...");

        System.out.println("Calculating sequential...");
        long start = System.currentTimeMillis();    
        MergeSortSeq.mergeSort(numbers);
        long seqTime = System.currentTimeMillis() - start;

        MergeSortForkJoin mergeSortForkJoin = new MergeSortForkJoin();
        System.out.println("Calculating fork/join...");
        start = System.currentTimeMillis();    
        mergeSortForkJoin.sort(numbers);
        long forkJoinTime = System.currentTimeMillis() - start;

        System.out.println("Time sequential: " + seqTime);
        System.out.println("Time fork/join: " + forkJoinTime);
    }
}
