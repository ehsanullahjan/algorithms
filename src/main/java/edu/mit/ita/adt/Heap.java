package edu.mit.ita.adt;

public interface Heap<T extends Comparable<? super T>> {
    void insert(T element);
    T remove();
    T peek();

    int size();
    boolean isEmpty();
}
