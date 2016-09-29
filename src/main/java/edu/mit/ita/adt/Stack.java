package edu.mit.ita.adt;

public interface Stack<T> extends Iterable<T> {
    void push(T element);
    T pop();
    T peek();
    int size();
    boolean isEmpty();
}
