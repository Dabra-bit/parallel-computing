package prac5;

public class MergeSortSeq {
    
    private int[] data;

    public MergeSortSeq(int[] data) {
        this.data = data;
    }

    public int[] sort() {
        return data;
    }

    private void divide(int[] a, int n) {
        if(n == 1) return;
        int half = n / 2;
        int[] l = new int[half];
        int[] r = new int[n - half];

        for(int i = 0; i < half; i++) {
            l[i] = a[i];
        }

        for(int i = half; i < n; i++) {
            r[i - half] = a[i];
        }

        divide(l, n - half);
        divide(r, n - half);

        merge(l, r, half, n - half);
    }

    private void merge(int[] l, int[] r, int left, int right) {
        int i = 0, j = 0, k = 0;
        while(i < left && j < right) {
            if(l[i] < r[j]) {
                data[k++] = l[i++];
            } else {
                data[k++] = r[j++];
            }
        }
        while(i < left) {
            data[k++] = l[i++];
        }
        while(j < right) {
            data[k++] = r[j++];
        }
    }
}
