package edu.mit.ita.util;

import static edu.mit.ita.util.Arrays.swap;
import static edu.mit.ita.util.Comparables.lt;

final class InsertionSort {
    private InsertionSort() {
    }

    static <T extends Comparable<? super T>> void sort(T[] seq) {
        // Trivially sorted
        if (seq == null || seq.length <= 1) {
            return;
        }

        // Loop invariant: seq[0..j-1] is always sorted
        for (int i = 1; i < seq.length; i++) {
            int j = i;
            while (j > 0 && lt(seq[j], seq[j - 1])) {
                swap(seq, j, j - 1);
                j--;
            }
        }
    }
}
