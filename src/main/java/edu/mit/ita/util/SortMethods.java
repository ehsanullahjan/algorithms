package edu.mit.ita.util;

public final class SortMethods {
    private SortMethods() {
    }

    public static <T extends Comparable<? super T>> void insertionSort(T[] seq) {
        InsertionSort.sort(seq);
    }

    public static <T extends Comparable<? super T>> void mergeSort(T[] seq) {
        MergeSort.sort(seq);
    }
}
