package edu.mit.ita.util;

public final class Comparables {
    private Comparables() {
    }

    public static <T extends Comparable<? super T>> T min(T[] seq) {
        return min(seq, 0, seq.length - 1);
    }

    public static <T extends Comparable<? super T>> T min(T[] seq, int lo, int hi) {
        validate(seq, lo, hi);

        T x = seq[lo];
        for (int i = lo + 1; i <= hi; i++) {
            if (lt(seq[i], x)) {
                x = seq[i];
            }
        }

        return x;
    }

    public static <T extends Comparable<? super T>> T max(T[] seq) {
        return max(seq, 0, seq.length - 1);
    }

    public static <T extends Comparable<? super T>> T max(T[] seq, int lo, int hi) {
        validate(seq, lo, hi);

        T x = seq[lo];
        for (int i = lo + 1; i <= hi; i++) {
            if (gt(seq[i], x)) {
                x = seq[i];
            }
        }

        return x;
    }

    public static <T extends Comparable<? super T>> boolean eq(T a, T b) {
        return a.compareTo(b) == 0;
    }

    public static <T extends Comparable<? super T>> boolean lt(T a, T b) {
        return a.compareTo(b) < 0;
    }

    public static <T extends Comparable<? super T>> boolean le(T a, T b) {
        return a.compareTo(b) <= 0;
    }

    public static <T extends Comparable<? super T>> boolean gt(T a, T b) {
        return a.compareTo(b) > 0;
    }

    public static <T extends Comparable<? super T>> boolean ge(T a, T b) {
        return a.compareTo(b) >= 0;
    }

    private static <T extends Comparable<? super T>> void validate(T[] seq, int lo, int hi) {
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
