package edu.mit.ita.util;

import static edu.mit.ita.util.Arrays.isTriviallySorted;
import static edu.mit.ita.util.Arrays.swap;
import static edu.mit.ita.util.Comparables.lt;

final class InsertionSort {
    private InsertionSort() {
    }

    static <T extends Comparable<? super T>> void sort(T[] seq) {
        if (isTriviallySorted(seq)) return;
        sort(seq, 0, seq.length - 1);
    }

    static <T extends Comparable<? super T>> void sort(T[] seq, int lo, int hi) {
        if (isTriviallySorted(seq)) return;

        // Loop invariant: seq[0..j-1] is always sorted
        for (int i = lo + 1; i <= hi; i++) {
            int j = i;
            while (j > 0 && lt(seq[j], seq[j - 1])) {
                swap(seq, j, j - 1);
                j--;
            }
        }
    }
}
