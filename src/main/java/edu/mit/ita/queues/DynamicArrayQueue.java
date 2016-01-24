package edu.mit.ita.queues;

import edu.mit.ita.adt.Queue;

import java.util.Iterator;

public class DynamicArrayQueue<T> implements Queue<T> {
    private T[] elements;
    private int front;
    private int size;

    public DynamicArrayQueue() {
        this(16);
    }

    @SuppressWarnings("unchecked")
    public DynamicArrayQueue(int initialCapacity) {
        this.elements = (T[])new Object[initialCapacity];
        this.front = 0;
        this.size = 0;
    }

    @Override
    public void enqueue(T element) {
        if (loadFactor() >= 1.0F) {
            resize(capacity() * 2);
        }

        int back = (front + size) % capacity();
        elements[back] = element;
        size++;
    }

    @Override
    public T dequeue() {
        T element = peek();
        elements[front] = null;
        front = (front + 1) % capacity();
        size--;

        if (loadFactor() <= 0.25F) {
            resize(capacity() / 2);
        }

        return element;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue underflow");
        }

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
    public Iterator<T> iterator() {
        return new ArrayQueueIterator<>(elements, front, size);
    }

    private float loadFactor() {
        return size / capacity();
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        T[] temp = (T[])new Object[capacity];
        for (int i = 0, j = front; i < size; i++, j = (j + 1) % capacity()) {
            temp[i] = elements[j];
        }

        elements = temp;
        front = 0;
    }
}
