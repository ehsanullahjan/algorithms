package edu.mit.ita.numerical;

public class Factorial {
    private Factorial() {
    }

    public static long recursive(long n) {
        if (n < 0) {
            throw new IllegalArgumentException("n < 0");
        }

        if (n == 0 || n == 1) {
            return n;
        } else {
            return n * Factorial.recursive(n - 1);
        }
    }

    public static long iterative(long n) {
        if (n < 0) {
            throw new IllegalArgumentException("n < 0");
        }

        long r = 1;
        for (long i = n; i > 0; i--) {
            r *= i;
        }

        return r;
    }
}
