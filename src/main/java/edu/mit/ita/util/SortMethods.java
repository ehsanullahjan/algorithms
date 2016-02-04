package edu.mit.ita.util;

import java.util.Comparator;

public final class SortMethods {
    private SortMethods() {
    }

    public static <T extends Comparable<? super T>> void insertionSort(T[] seq) {
        insertionSort(seq, Comparable::compareTo);
    }

    public static <T> void insertionSort(T[] seq, Comparator<? super T> comparator) {
        InsertionSort.sort(seq, comparator);
    }

    public static <T extends Comparable<? super T>> void mergeSort(T[] seq) {
        mergeSort(seq, Comparable::compareTo);
    }

    public static <T> void mergeSort(T[] seq, Comparator<? super T> comparator) {
        MergeSort.sort(seq, comparator);
    }
}
