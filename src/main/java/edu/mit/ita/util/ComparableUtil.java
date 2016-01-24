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
            if (lt(seq[i], x)) {
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
            if (gt(seq[i], x)) {
                x = seq[i];
            }
        }

        return x;
    }

    public static <T extends Comparable<T>> boolean eq(T a, T b) {
        return a.compareTo(b) == 0;
    }

    public static <T extends Comparable<T>> boolean lt(T a, T b) {
        return a.compareTo(b) < 0;
    }

    public static <T extends Comparable<T>> boolean ltOrEq(T a, T b) {
        return a.compareTo(b) <= 0;
    }

    public static <T extends Comparable<T>> boolean gt(T a, T b) {
        return a.compareTo(b) > 0;
    }

    public static <T extends Comparable<T>> boolean gtOrEq(T a, T b) {
        return a.compareTo(b) >= 0;
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
