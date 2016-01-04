package edu.mit.ita.sorting;

public class Merge {
    private Merge() {
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void sort(T[] a) {
        sort(a, (T[])new Comparable[a.length], 0, a.length - 1);
    }

    private static <T extends Comparable<T>> void sort(T[] a, T[] b, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int mid = (lo + hi) / 2;
        sort(a, b, lo, mid);
        sort(a, b, mid + 1, hi);
        merge(a, b, lo, mid, hi);
    }

    @SuppressWarnings("ManualArrayCopy")
    private static <T extends Comparable<T>> void merge(T[] a, T[] b, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            b[i] = a[i];
        }

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = b[j++];
            } else if (j > hi) {
                a[k] = b[i++];
            } else if (b[i].compareTo(b[j]) < 0) {
                a[k] = b[i++];
            } else {
                a[k] = b[j++];
            }
        }
    }
}
