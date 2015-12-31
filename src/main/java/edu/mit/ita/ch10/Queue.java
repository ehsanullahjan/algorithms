package edu.mit.ita.ch10;

public interface Queue<T> {
    void enqueue(T element);
    T dequeue();
    T peek();

    int size();
    boolean isEmpty();
}
