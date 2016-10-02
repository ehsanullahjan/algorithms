package edu.mit.ita.util;

public class Numerics {
    private Numerics() {
    }

    public static long factorial(int n) {
        if (n < 0)
            throw new IllegalArgumentException("n < 0");

        long fact = 1;
        for (long num = n; num > 0; num--) {
            fact *= num;
        }
        return fact;
    }

    public static long fibonacci(int n) {
        if (n < 0)
            throw new IllegalArgumentException("n < 0");

        if (n < 2)
            return n;

        int prev = 0;
        int curr = 1;
        for (int i = 0; i < n; i++) {
            int next = prev + curr;
            prev = curr;
            curr = next;
        }
        return curr;
    }
}
