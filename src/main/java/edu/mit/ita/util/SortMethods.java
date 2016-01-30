package edu.mit.ita.util;

import edu.mit.ita.adt.SortMethod;

import java.util.Comparator;

import static edu.mit.ita.util.Arrays.swap;

public class SortMethods {
    public static final SortMethod insertionSort = new SortMethod() {
        @Override
        public <T extends Comparable<? super T>> void apply(T[] seq) {
            sort(seq, Comparable::compareTo);
        }

        @Override
        public <T> void apply(T[] seq, Comparator<? super T> comparator) {
            sort(seq, comparator);
        }

        private <T> void sort(T[] seq, Comparator<? super T> comparator) {
            if (comparator == null) {
                throw new NullPointerException("comparator");
            }

            // Trivially sorted
            if (seq == null || seq.length <= 1) {
                return;
            }

            // Loop invariant: seq[0..j-1] is always sorted
            for (int i = 1; i < seq.length; i++) {
                for (int j = i; j > 0; j--) {
                    if (comparator.compare(seq[j], seq[j - 1]) < 0) {
                        swap(seq, j, j - 1);
                    } else {
                        break;
                    }
                }
            }
        }
    };

    public static final SortMethod mergeSort = new SortMethod() {
        @Override
        public <T extends Comparable<? super T>> void apply(T[] seq) {
            sort(seq, Comparable::compareTo);
        }

        @Override
        public <T> void apply(T[] seq, Comparator<? super T> comparator) {
            sort(seq, comparator);
        }

        @SuppressWarnings("unchecked")
        private <T> void sort(T[] seq, Comparator<? super T> comparator) {
            if (comparator == null) {
                throw new NullPointerException("comparator");
            }

            if (seq == null || seq.length <= 1) {
                return;
            }

            T[] auxSeq = (T[])new Comparable<?>[seq.length];
            sort(seq, comparator, auxSeq, 0, seq.length - 1);
        }

        private <T> void sort(T[] seq, Comparator<? super T> comparator, T[] auxSeq, int lo, int hi) {
            if (lo >= hi) {
                return;
            }

            int mid = (lo + hi) / 2;
            sort(seq, comparator, auxSeq, lo, mid);
            sort(seq, comparator, auxSeq, mid + 1, hi);
            merge(seq, comparator, auxSeq, lo, mid, hi);
        }

        // Precondition: seq[lo..mid] and seq[mid+1..hi] must be sorted
        @SuppressWarnings("ManualArrayCopy")
        private <T> void merge(T[] seq, Comparator<? super T> comparator, T[] auxSeq, int lo, int mid, int hi) {
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
                } else if (comparator.compare(auxSeq[i], auxSeq[j]) < 0) {
                    seq[k] = auxSeq[i++];
                } else {
                    seq[k] = auxSeq[j++];
                }
            }
        }
    };
}
