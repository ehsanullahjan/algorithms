package edu.mit.ita.adt;

public interface Stack<E> extends Iterable<E> {
    void push(E element);
    E pop();
    E peek();
    int size();
    boolean isEmpty();
}
