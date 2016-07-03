package edu.mit.ita.util;

public class BinarySearch {
    private BinarySearch() {
    }

    public static <T extends Comparable<? super T>> int rank(T[] seq, T key) {
        return rank(seq, key, 0, seq.length);
    }

    private static <T extends Comparable<? super T>> int rank(T[] seq, T key, int lo, int hi) {
        if (lo > hi) {
            return -1;
        }

        int mid = lo + (hi - lo) / 2;
        int relation = key.compareTo(seq[mid]);
        if (relation < 0) {
            return rank(seq, key, lo, mid - 1);
        } else if (relation > 0) {
            return rank(seq, key, mid + 1, hi);
        } else {
            return mid;
        }
    }
}
