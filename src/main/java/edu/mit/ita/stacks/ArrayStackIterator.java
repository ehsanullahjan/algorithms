package edu.mit.ita.stacks;

import java.util.Iterator;
import java.util.NoSuchElementException;

class ArrayStackIterator<T> implements Iterator<T> {
    private final T[] elements;
    private int top;

    ArrayStackIterator(T[] elements, int top) {
        assert elements != null;
        assert top < elements.length;

        this.elements = elements;
        this.top = top;
    }

    @Override
    public boolean hasNext() {
        return top >= 0;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        return elements[top--];
    }
}
