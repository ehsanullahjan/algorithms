package edu.mit.ita.numerical;

public final class Fibonacci {
    private Fibonacci() {
    }

    public static long recursive(long n) {
        if (n < 0)
            throw new IllegalArgumentException("n < 0");

        if (n < 2)
            return n;

        return Fibonacci.recursive(n - 1) + Fibonacci.recursive(n - 2);
    }

    public static long dynamic(long n) {
        if (n < 0)
            throw new IllegalArgumentException("n < 0");

        int r = 0;
        int a = 0;
        int b = 1;
        for (int i = 0; i < n; i++) {
            r = a + b;
            a = b;
            b = r;
        }

        return r;
    }
}
