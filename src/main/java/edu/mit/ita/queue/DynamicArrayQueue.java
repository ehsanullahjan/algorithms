package edu.mit.ita.queue;

import edu.mit.ita.adt.Queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicArrayQueue<E> implements Queue<E> {
    private E[] elements;
    private int front;
    private int size;

    public DynamicArrayQueue() {
        this(16);
    }

    @SuppressWarnings("unchecked")
    public DynamicArrayQueue(int initialCapacity) {
        this.elements = (E[])new Object[initialCapacity];
        this.front = 0;
        this.size = 0;
    }

    @Override
    public void enqueue(E element) {
        if (loadFactor() >= 1.0F)
            resize(capacity() * 2);

        int back = (front + size) % capacity();
        elements[back] = element;
        size++;
    }

    @Override
    public E dequeue() {
        E element = peek();
        elements[front] = null;
        front = (front + 1) % capacity();
        size--;

        if (loadFactor() <= 0.25F)
            resize(capacity() / 2);

        return element;
    }

    @Override
    public E peek() {
        if (isEmpty())
            throw new NoSuchElementException();

        return elements[front];
    }

    @Override
    public int size() {
        return size;
    }

    public int capacity() {
        return elements.length;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayQueueIterator<>(elements, front, size);
    }

    private float loadFactor() {
        return (float)size / (float)capacity();
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        E[] temp = (E[])new Object[capacity];
        for (int i = 0, j = front; i < size; i++, j = (j + 1) % capacity()) {
            temp[i] = elements[j];
        }

        elements = temp;
        front = 0;
    }
}
