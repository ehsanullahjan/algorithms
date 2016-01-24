package edu.mit.ita.adt;

public interface Queue<T> extends Iterable<T> {
    void enqueue(T element);
    T dequeue();
    T peek();

    int size();
    boolean isEmpty();
}
