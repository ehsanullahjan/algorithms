package edu.mit.ita.adt;

public interface SortMethod {
    <T extends Comparable<T>> void apply(T[] seq);
}
