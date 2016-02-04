package edu.mit.ita.util;

import java.util.Comparator;

final class MergeSort {
    private MergeSort() {
    }

    @SuppressWarnings("unchecked")
    static <T> void sort(T[] seq, Comparator<? super T> comparator) {
        if (comparator == null) {
            throw new NullPointerException("comparator");
        }

        // Trivially sorted
        if (seq == null || seq.length <= 1) {
            return;
        }

        T[] auxSeq = (T[])new Comparable<?>[seq.length];
        sort(seq, comparator, auxSeq, 0, seq.length - 1);
    }

    private static <T> void sort(T[] seq, Comparator<? super T> comparator, T[] auxSeq, int lo, int hi) {
        if (lo >= hi) {
            return;
        }

        int mid = (lo + hi) / 2;
        sort(seq, comparator, auxSeq, lo, mid);
        sort(seq, comparator, auxSeq, mid + 1, hi);
        merge(seq, comparator, auxSeq, lo, mid, hi);
    }

    // Precondition: seq[lo..mid] and seq[mid+1..hi] must be sorted
    private static <T> void merge(T[] seq, Comparator<? super T> comparator, T[] auxSeq, int lo, int mid, int hi) {
        System.arraycopy(seq, lo, auxSeq, lo, hi - lo + 1);

        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                seq[k] = auxSeq[j++];
            } else if (j > hi) {
                seq[k] = auxSeq[i++];
            } else if (comparator.compare(auxSeq[i], auxSeq[j]) < 0) {
                seq[k] = auxSeq[i++];
            } else {
                seq[k] = auxSeq[j++];
            }
        }
    }
}
