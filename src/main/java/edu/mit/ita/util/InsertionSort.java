package edu.mit.ita.util;

import java.util.Comparator;

import static edu.mit.ita.util.Arrays.swap;

final class InsertionSort {
    private InsertionSort() {
    }

    static <T> void sort(T[] seq, Comparator<? super T> comparator) {
        if (comparator == null) {
            throw new NullPointerException("comparator");
        }

        // Trivially sorted
        if (seq == null || seq.length <= 1) {
            return;
        }

        // Loop invariant: seq[0..j-1] is always sorted
        for (int i = 1; i < seq.length; i++) {
            int j = i;
            while (j > 0 && comparator.compare(seq[j], seq[j - 1]) < 0) {
                swap(seq, j, j - 1);
                j--;
            }
        }
    }
}
