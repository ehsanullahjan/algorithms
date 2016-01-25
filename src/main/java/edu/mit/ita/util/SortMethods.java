package edu.mit.ita.util;

import edu.mit.ita.adt.SortMethod;

import static edu.mit.ita.util.Arrays.swap;
import static edu.mit.ita.util.Comparables.lt;

public class SortMethods {
    public static final SortMethod insertionSort = new SortMethod() {
        public <T extends Comparable<T>> void apply(T[] seq) {
            sort(seq);
        }

        public <T extends Comparable<T>> void sort(T[] seq) {
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
    };

    public static final SortMethod mergeSort = new SortMethod() {
        public <T extends Comparable<T>> void apply(T[] seq) {
            sort(seq);
        }

        @SuppressWarnings("unchecked")
        public <T extends Comparable<T>> void sort(T[] seq) {
            if (seq == null || seq.length <= 1) {
                return;
            }

            T[] auxSeq = (T[])new Comparable[seq.length];
            sort(seq, auxSeq, 0, seq.length - 1);
        }

        private <T extends Comparable<T>> void sort(T[] seq, T[] auxSeq, int lo, int hi) {
            if (lo >= hi) {
                return;
            }

            int mid = (lo + hi) / 2;
            sort(seq, auxSeq, lo, mid);
            sort(seq, auxSeq, mid + 1, hi);
            merge(seq, auxSeq, lo, mid, hi);
        }

        // Precondition: seq[lo..mid] and seq[mid+1..hi] must be sorted
        @SuppressWarnings("ManualArrayCopy")
        private <T extends Comparable<T>> void merge(T[] seq, T[] auxSeq, int lo, int mid, int hi) {
            for (int i = lo; i <= hi; i++) {
                auxSeq[i] = seq[i];
            }

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
    };
}
