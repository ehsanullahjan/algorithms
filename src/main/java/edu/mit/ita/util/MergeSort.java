package edu.mit.ita.util;

import static edu.mit.ita.util.Arrays.isSorted;
import static edu.mit.ita.util.Arrays.isTriviallySorted;
import static edu.mit.ita.util.Comparables.lt;

final class MergeSort {
    private MergeSort() {
    }

    @SuppressWarnings("unchecked")
    static <T extends Comparable<? super T>> void sort(T[] seq) {
        if (isTriviallySorted(seq)) return;

        T[] auxSeq = (T[])new Comparable<?>[seq.length];
        sort(seq, auxSeq, 0, seq.length - 1);
    }

    private static <T extends Comparable<? super T>> void sort(T[] seq, T[] auxSeq, int lo, int hi) {
        if (isTriviallySorted(seq, lo, hi)) return;

        int mid = lo + (hi - lo) / 2;
        sort(seq, auxSeq, lo, mid);
        sort(seq, auxSeq, mid + 1, hi);
        merge(seq, auxSeq, lo, mid, hi);
    }

    private static <T extends Comparable<? super T>> void merge(T[] seq, T[] auxSeq, int lo, int mid, int hi) {
        assert isSorted(seq, lo, mid);
        assert isSorted(seq, mid + 1, hi);

        // Copy seq[lo..mid] to auxSeq[lo..mid] and seq[hi..mid+1] to auxSeq[mid+1..hi]
        int i = lo, j = hi, k = lo;
        while (i <= mid) auxSeq[k++] = seq[i++];
        while (j > mid) auxSeq[k++] = seq[j--];

        // Merge (in sorted order) auxSeq[lo..mid] and auxSeq[hi..mid+1] into seq[lo..hi]
        for (i = lo, j = hi, k = lo; k <= hi; k++) {
            if (lt(auxSeq[i], auxSeq[j])) {
                seq[k] = auxSeq[i++];
            } else {
                seq[k] = auxSeq[j--];
            }
        }
    }
}
