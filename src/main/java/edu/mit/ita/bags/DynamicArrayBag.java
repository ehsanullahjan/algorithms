package edu.mit.ita.bags;

import edu.mit.ita.adt.Collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicArrayBag<T> implements Collection<T> {
    private T[] elements;
    private int pointer;

    @SuppressWarnings("unchecked")
    public DynamicArrayBag() {
        this(16);
    }

    @SuppressWarnings("unchecked")
    public DynamicArrayBag(int initialCapacity) {
        this.elements = (T[])new Object[initialCapacity];
        this.pointer = 0;
    }

    @Override
    public void add(T element) {
        if (isFull()) {
            grow();
        }

        elements[pointer++] = element;
    }

    @Override
    @SuppressWarnings("ManualArrayCopy")
    public boolean remove(T element) {
        int i = indexOf(element);
        if (i < 0) {
            return false;
        }

        elements[i] = null;
        for (int j = i; j < pointer - 1; j++) {
            elements[j] = elements[j + 1];
        }
        pointer--;

        if (isQuarterFull()) {
            shrink();
        }

        return true;
    }

    @Override
    public boolean contains(T element) {
        return indexOf(element) >= 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < elements.length; i++) {
            elements[i] = null;
        }

        pointer = 0;
    }

    @Override
    public int size() {
        return pointer;
    }

    public int capacity() {
        return elements.length;
    }

    @Override
    public boolean isEmpty() {
        return pointer == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int i = 0;
            private final int size = pointer;

            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                if (size != pointer) {
                    throw new ConcurrentModificationException();
                }

                return elements[i++];
            }
        };
    }

    private int indexOf(T element) {
        if (isEmpty() || element == null) {
            return -1;
        }

        for (int i = 0; i < pointer; i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }

    private boolean isFull() {
        return pointer == elements.length;
    }

    private boolean isQuarterFull() {
        return pointer == elements.length / 4;
    }

    private void grow() {
        resize(elements.length * 2);
    }

    private void shrink() {
        resize(elements.length / 2);
    }

    @SuppressWarnings({"unchecked", "ManualArrayCopy"})
    private void resize(int capacity) {
        T[] temp = (T[])new Object[capacity];
        for (int i = 0; i < pointer; i++) {
            temp[i] = elements[i];
        }

        elements = temp;
    }
}
