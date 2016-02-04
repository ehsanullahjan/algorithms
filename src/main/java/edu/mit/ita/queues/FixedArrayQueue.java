package edu.mit.ita.queues;

import edu.mit.ita.adt.Queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FixedArrayQueue<T> implements Queue<T> {
    private final T[] elements;
    private int front;
    private int size;

    @SuppressWarnings("unchecked")
    public FixedArrayQueue(int capacity) {
        this.elements = (T[])new Object[capacity];
        this.front = 0;
        this.size = 0;
    }

    @Override
    public void enqueue(T element) {
        if (isFull()) {
            throw new IllegalStateException("Queue overflow");
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

        return element;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
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

    public boolean isFull() {
        return size == capacity();
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayQueueIterator<>(elements, front, size);
    }
}
