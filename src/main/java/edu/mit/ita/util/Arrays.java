package edu.mit.ita.util;

import static edu.mit.ita.util.Comparables.lt;

public final class Arrays {
    private Arrays() {
    }

    public static <T> void swap(T[] seq, int i, int j) {
        if (i == j) {
            return;
        }

        T temp = seq[i];
        seq[i] = seq[j];
        seq[j] = temp;
    }

    public static <E> boolean isTriviallySorted(E[] seq, int lo, int hi) {
        return isTriviallySorted(seq) || lo == hi;
    }

    public static <E> boolean isTriviallySorted(E[] seq) {
        return seq == null || seq.length <= 1;
    }

    public static <E extends Comparable<? super E>> boolean isSorted(E[] seq) {
        return isSorted(seq, 0, seq.length - 1);
    }

    public static <E extends Comparable<? super E>> boolean isSorted(E[] seq, int lo, int hi) {
        for (int i = hi; i > lo; i--) {
            if (lt(seq[i], seq[i - 1])) return false;
        }
        return true;
    }
}
