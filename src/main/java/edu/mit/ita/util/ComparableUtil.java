package edu.mit.ita.util;

public class ComparableUtil {
    private ComparableUtil() {
    }

    public static <T extends Comparable<T>> T min(T[] seq) {
        return min(seq, 0, seq.length);
    }

    public static <T extends Comparable<T>> T min(T[] seq, int lo, int hi) {
        validate(seq, lo, hi);

        T x = seq[lo];
        for (int i = lo + 1; i < hi; i++) {
            if (seq[i].compareTo(x) < 0) {
                x = seq[i];
            }
        }

        return x;
    }

    public static <T extends Comparable<T>> T max(T[] seq) {
        return max(seq, 0, seq.length);
    }

    public static <T extends Comparable<T>> T max(T[] seq, int lo, int hi) {
        validate(seq, lo, hi);

        T x = seq[lo];
        for (int i = lo + 1; i < hi; i++) {
            if (seq[i].compareTo(x) > 0) {
                x = seq[i];
            }
        }

        return x;
    }

    private static <T extends Comparable<T>> void validate(T[] seq, int lo, int hi) {
        if (seq == null || seq.length == 0) {
            throw new IllegalArgumentException("seq is null or empty");
        }

        if (lo < 0 || lo >= hi) {
            throw new IllegalArgumentException("lo < 0 or lo >= hi");
        }

        if (hi > seq.length) {
            throw new IllegalArgumentException("hi > seq.length");
        }
    }
}
