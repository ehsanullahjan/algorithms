package edu.mit.ita.adt;

import java.util.Comparator;

public interface SortMethod {
    <T extends Comparable<? super T>> void apply(T[] seq);
    <T> void apply(T[] seq, Comparator<? super T> comparator);
}
