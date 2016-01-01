package edu.mit.ita.adt;

public interface Collection<T> extends Iterable<T> {
    void add(T element);
    boolean remove(T element);
    boolean contains(T element);
    void clear();

    int size();
    boolean isEmpty();
}
