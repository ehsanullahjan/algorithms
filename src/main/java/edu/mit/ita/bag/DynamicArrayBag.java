package edu.mit.ita.bag;

import edu.mit.ita.adt.Collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.lang.System.arraycopy;

public class DynamicArrayBag<E> implements Collection<E> {
    private E[] elements;
    private int index;

    public DynamicArrayBag() {
        this(16);
    }

    @SuppressWarnings("unchecked")
    public DynamicArrayBag(int initialCapacity) {
        this.elements = (E[])new Object[initialCapacity];
        this.index = 0;
    }

    @Override
    public void add(E element) {
        if (loadFactor() >= 1.0F)
            resize(capacity() * 2);

        elements[index++] = element;
    }

    @Override
    public boolean remove(E element) {
        int i = indexOf(element);
        if (i < 0) return false;

        elements[i] = elements[--index];
        elements[index] = null;

        if (loadFactor() <= 0.25F)
            resize(capacity() / 2);

        return true;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) >= 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size(); i++) {
            elements[i] = null;
        }

        index = 0;
    }

    @Override
    public int size() {
        return index;
    }

    public int capacity() {
        return elements.length;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int i = 0;
            private final int size = size();

            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public E next() {
                if (!hasNext())
                    throw new NoSuchElementException();

                if (size != size())
                    throw new ConcurrentModificationException();

                return elements[i++];
            }
        };
    }

    private int indexOf(E element) {
        if (isEmpty() || element == null)
            return -1;

        for (int i = 0; i < index; i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }

    private float loadFactor() {
        return (float)size() / (float)capacity();
    }

    @SuppressWarnings({"unchecked"})
    private void resize(int capacity) {
        E[] temp = (E[])new Object[capacity];
        arraycopy(elements, 0, temp, 0, size());
        elements = temp;
    }
}
