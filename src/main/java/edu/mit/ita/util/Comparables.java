package edu.mit.ita.util;

public final class Comparables {
    private Comparables() {
    }

    public static <E extends Comparable<? super E>> E min(E[] seq) {
        return min(seq, 0, seq.length - 1);
    }

    public static <E extends Comparable<? super E>> E min(E[] seq, int lo, int hi) {
        validate(seq, lo, hi);

        E x = seq[lo];
        for (int i = lo + 1; i <= hi; i++) {
            if (lt(seq[i], x)) {
                x = seq[i];
            }
        }

        return x;
    }

    public static <E extends Comparable<? super E>> E max(E[] seq) {
        return max(seq, 0, seq.length - 1);
    }

    public static <E extends Comparable<? super E>> E max(E[] seq, int lo, int hi) {
        validate(seq, lo, hi);

        E x = seq[lo];
        for (int i = lo + 1; i <= hi; i++) {
            if (gt(seq[i], x)) {
                x = seq[i];
            }
        }

        return x;
    }

    public static <E extends Comparable<? super E>> boolean eq(E a, E b) {
        return a.compareTo(b) == 0;
    }

    public static <E extends Comparable<? super E>> boolean lt(E a, E b) {
        return a.compareTo(b) < 0;
    }

    public static <E extends Comparable<? super E>> boolean le(E a, E b) {
        return a.compareTo(b) <= 0;
    }

    public static <E extends Comparable<? super E>> boolean gt(E a, E b) {
        return a.compareTo(b) > 0;
    }

    public static <E extends Comparable<? super E>> boolean ge(E a, E b) {
        return a.compareTo(b) >= 0;
    }

    private static <E extends Comparable<? super E>> void validate(E[] seq, int lo, int hi) {
        if (seq == null || seq.length == 0)
            throw new IllegalArgumentException("seq is null or empty");

        if (lo < 0 || lo > hi)
            throw new IllegalArgumentException("lo < 0 or lo > hi");

        if (hi > seq.length)
            throw new IllegalArgumentException("hi > seq.length");
    }
}
