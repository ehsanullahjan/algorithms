package edu.mit.ita.sorting;

public class Insertion {
    private Insertion() {
    }

    public static <T extends Comparable<T>> void sort(T[] seq) {
        // Trivially sorted
        if (seq.length <= 1) {
            return;
        }

        // Loop invariant: seq[0..j-1] is always sorted
        for (int i = 1; i < seq.length; i++) {
            for (int j = i; j > 0; j--) {
                if (seq[j].compareTo(seq[j - 1]) < 0) {
                    swap(seq, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    public static <T extends Comparable<T>> void swap(T[] seq, int i, int j) {
        if (i == j) {
            return;
        }

        T x = seq[i];
        seq[i] = seq[j];
        seq[j] = x;
    }
}
