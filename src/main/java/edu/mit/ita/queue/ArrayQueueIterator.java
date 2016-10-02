package edu.mit.ita.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

class ArrayQueueIterator<E> implements Iterator<E> {
    private final E[] elements;
    private final int front;
    private final int size;
    private int index;

    ArrayQueueIterator(E[] elements, int front, int size) {
        this.elements = elements;
        this.front = front;
        this.size = size;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < size;
    }

    @Override
    public E next() {
        if (!hasNext())
            throw new NoSuchElementException();

        return elements[(front + index++) % elements.length];
    }
}
