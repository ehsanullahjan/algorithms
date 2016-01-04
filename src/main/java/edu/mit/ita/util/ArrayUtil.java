package edu.mit.ita.util;

public class ArrayUtil {
    private ArrayUtil() {
    }

    public static <T extends Comparable<T>> void swap(T[] a, int i, int j) {
        if (i == j) {
            return;
        }

        T x = a[i];
        a[i] = a[j];
        a[j] = x;
    }
}
