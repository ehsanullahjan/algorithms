package edu.mit.ita.adt;

public interface Queue<E> extends Iterable<E> {
    void enqueue(E element);
    E dequeue();
    E peek();
    int size();
    boolean isEmpty();
}
