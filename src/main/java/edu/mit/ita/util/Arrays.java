package edu.mit.ita.util;

public class Arrays {
    private Arrays() {
    }

    public static <T extends Comparable<T>> void swap(T[] seq, int i, int j) {
        if (i == j) {
            return;
        }

        T temp = seq[i];
        seq[i] = seq[j];
        seq[j] = temp;
    }
}