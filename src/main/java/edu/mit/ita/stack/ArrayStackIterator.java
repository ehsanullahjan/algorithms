package edu.mit.ita.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

class ArrayStackIterator<E> implements Iterator<E> {
    private final E[] elements;
    private int top;

    ArrayStackIterator(E[] elements, int top) {
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
    public E next() {
        if (!hasNext())
            throw new NoSuchElementException();

        return elements[top--];
    }
}
