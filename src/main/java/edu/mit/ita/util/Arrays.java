package edu.mit.ita.util;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static edu.mit.ita.util.Comparables.*;

public final class Arrays {
    private static final Random random = new Random();

    private Arrays() {
    }

    public static <E> void swap(E[] seq, int i, int j) {
        if (i == j) return;

        E temp = seq[i];
        seq[i] = seq[j];
        seq[j] = temp;
    }

    public static Integer[] randomIntSeq(int size) {
        Integer[] seq = new Integer[size];
        for (int i = 0; i < size; i++) {
            seq[i] = random.nextInt();
        }
        return seq;
    }

    public static <E> void shuffle(E[] seq) {
        if (isTriviallySorted(seq)) return;

        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < seq.length - 1; i++) {
            int j = random.nextInt(i, seq.length);
            swap(seq, i, j);
        }
    }

    public static <E extends Comparable<? super E>> int binarySearch(E[] seq, E key) {
        return binarySearch(seq, key, 0, seq.length);
    }

    public static <E extends Comparable<? super E>> void insertionSort(E[] seq) {
        if (isTriviallySorted(seq)) return;
        insertionSort(seq, 0, seq.length - 1);
    }

    @SuppressWarnings("unchecked")
    public static <E extends Comparable<? super E>> void mergeSort(E[] seq) {
        if (isTriviallySorted(seq)) return;

        E[] auxSeq = (E[])new Comparable<?>[seq.length];
        mergeSort(seq, auxSeq, 0, seq.length - 1);
    }

    public static <E extends Comparable<? super E>> void quickSort(E[] seq) {
        if (isTriviallySorted(seq)) return;

        quickSort(seq, 0, seq.length - 1);
        assert isSorted(seq);
    }

    private static <E extends Comparable<? super E>> int binarySearch(E[] seq, E key, int lo, int hi) {
        if (lo > hi) return -1;

        int mid = lo + (hi - lo) / 2;
        int relation = key.compareTo(seq[mid]);
        if (relation < 0)
            return binarySearch(seq, key, lo, mid - 1);
        else if (relation > 0)
            return binarySearch(seq, key, mid + 1, hi);
        else
            return mid;
    }

    private static <E extends Comparable<? super E>> void insertionSort(E[] seq, int lo, int hi) {
        if (isTriviallySorted(seq, lo, hi)) return;

        // Loop invariant: seq[0..j-1] is always sorted
        for (int i = lo + 1; i <= hi; i++) {
            int j = i;
            while (j > lo && lt(seq[j], seq[j - 1])) {
                swap(seq, j, j - 1);
                j--;
            }
        }
    }

    private static <E extends Comparable<? super E>> void mergeSort(E[] seq, E[] auxSeq, int lo, int hi) {
        if (isTriviallySorted(seq, lo, hi)) return;

        int mid = lo + (hi - lo) / 2;
        bestSort(seq, auxSeq, lo, mid);
        bestSort(seq, auxSeq, mid + 1, hi);
        merge(seq, auxSeq, lo, mid, hi);
    }

    private static <E extends Comparable<? super E>> void bestSort(E[] seq, E[] auxSeq, int lo, int hi) {
        final int altSortThreshold = 7;
        int elementsToSort = hi - lo + 1;
        if (elementsToSort > altSortThreshold)
            mergeSort(seq, auxSeq, lo, hi);
        else
            insertionSort(seq, lo, hi);
    }

    private static <E extends Comparable<? super E>> void merge(E[] seq, E[] auxSeq, int lo, int mid, int hi) {
        assert isSorted(seq, lo, mid);
        assert isSorted(seq, mid + 1, hi);

        // Copy seq[lo..mid] to auxSeq[lo..mid] and seq[hi..mid+1] to auxSeq[mid+1..hi]
        int i = lo, j = hi, k = lo;
        while (i <= mid) auxSeq[k++] = seq[i++];
        while (j > mid) auxSeq[k++] = seq[j--];

        // Merge (in sorted order) auxSeq[lo..mid] and auxSeq[hi..mid+1] into seq[lo..hi]
        for (i = lo, j = hi, k = lo; k <= hi; k++) {
            if (le(auxSeq[i], auxSeq[j]))
                seq[k] = auxSeq[i++];
            else
                seq[k] = auxSeq[j--];
        }
    }

    private static <E extends Comparable<? super E>> void quickSort(E[] seq, int lo, int hi) {
        if (lo >= hi) return;

        swap(seq, pivot(seq, lo, hi), hi);
        int p = partition(seq, lo, hi - 1, seq[hi]);
        swap(seq, p, hi);

        quickSort(seq, lo, p - 1);
        quickSort(seq, p + 1, hi);
    }

    private static <E> int pivot(E[] seq, int lo, int hi) {
        return lo + (hi - lo) / 2;
    }

    private static <E extends Comparable<? super E>> int partition(E[] seq, int lo, int hi, E pivot) {
        int i = lo;
        int j = hi;
        while (i <= j) {
            while (i <= j && le(seq[i], pivot)) i++;
            while (j >= i && gt(seq[j], pivot)) j--;
            if (i < j) swap(seq, i, j);
        }
        return i;
    }

    private static <E> boolean isTriviallySorted(E[] seq, int lo, int hi) {
        return isTriviallySorted(seq) || lo == hi;
    }

    private static <E> boolean isTriviallySorted(E[] seq) {
        return seq == null || seq.length <= 1;
    }

    static <E extends Comparable<? super E>> boolean isSorted(E[] seq) {
        return isSorted(seq, 0, seq.length - 1);
    }

    static <E extends Comparable<? super E>> boolean isSorted(E[] seq, int lo, int hi) {
        for (int i = hi; i > lo; i--) {
            if (lt(seq[i], seq[i - 1])) return false;
        }
        return true;
    }
}
