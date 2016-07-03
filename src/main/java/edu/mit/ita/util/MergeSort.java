package edu.mit.ita.util;

import static edu.mit.ita.util.Comparables.lt;

final class MergeSort {
    private MergeSort() {
    }

    @SuppressWarnings("unchecked")
    static <T extends Comparable<? super T>> void sort(T[] seq) {
        // Trivially sorted
        if (seq == null || seq.length <= 1) {
            return;
        }

        T[] auxSeq = (T[])new Comparable<?>[seq.length];
        sort(seq, auxSeq, 0, seq.length - 1);
    }

    private static <T extends Comparable<? super T>> void sort(T[] seq, T[] auxSeq, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int mid = lo + (hi - lo) / 2;
        sort(seq, auxSeq, lo, mid);
        sort(seq, auxSeq, mid + 1, hi);
        merge(seq, auxSeq, lo, mid, hi);
    }

    // Precondition: seq[lo..mid] and seq[mid+1..hi] must be sorted
    private static <T extends Comparable<? super T>> void merge(T[] seq, T[] auxSeq, int lo, int mid, int hi) {
        int i, j, k;

        // Copy seq[lo..mid] to auxSeq[lo..mid]
        for (i = lo, k = lo; i <= mid; i++, k++) {
            auxSeq[k] = seq[i];
        }

        // Reverse copy seq[hi..mid+1] to auxSeq[mid+1..hi]
        for (j = hi; j > mid; j--, k++) {
            auxSeq[k] = seq[j];
        }

        // Merge auxSeq[lo..mid] and auxSeq[hi..mid+1] back into seq[lo..hi]
        for (i = lo, j = hi, k = lo; k <= hi; k++) {
            if (lt(auxSeq[i], auxSeq[j])) {
                seq[k] = auxSeq[i++];
            } else {
                seq[k] = auxSeq[j--];
            }
        }
    }
}
