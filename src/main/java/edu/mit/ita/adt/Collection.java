package edu.mit.ita.adt;

public interface Collection<E> extends Iterable<E> {
    void add(E element);
    boolean remove(E element);
    boolean contains(E element);
    void clear();
    int size();
    boolean isEmpty();
}
