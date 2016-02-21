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

        int mid = (lo + hi) / 2;
        sort(seq, auxSeq, lo, mid);
        sort(seq, auxSeq, mid + 1, hi);
        merge(seq, auxSeq, lo, mid, hi);
    }

    // Precondition: seq[lo..mid] and seq[mid+1..hi] must be sorted
    private static <T extends Comparable<? super T>> void merge(T[] seq, T[] auxSeq, int lo, int mid, int hi) {
        System.arraycopy(seq, lo, auxSeq, lo, hi - lo + 1);

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                seq[k] = auxSeq[j++];
            } else if (j > hi) {
                seq[k] = auxSeq[i++];
            } else if (lt(auxSeq[i], auxSeq[j])) {
                seq[k] = auxSeq[i++];
            } else {
                seq[k] = auxSeq[j++];
            }
        }
    }
}
