package edu.mit.ita.queues;

import edu.mit.ita.adt.Queue;

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
        if (isFull()) {
            grow();
        }

        int back = (front + size) % elements.length;
        elements[back] = element;
        size++;
    }

    @Override
    public T dequeue() {
        T element = peek();
        elements[front] = null;
        front = (front + 1) % elements.length;
        size--;

        if (isQuarterFull()) {
            shrink();
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

    private boolean isFull() {
        return size == elements.length;
    }

    private boolean isQuarterFull() {
        return size == elements.length / 4;
    }

    private void grow() {
        resize(elements.length * 2);
    }

    private void shrink() {
        resize(elements.length / 2);
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        T[] temp = (T[])new Object[capacity];
        for (int i = 0, j = front; i < size; i++, j = (j + 1) % elements.length) {
            temp[i] = elements[j];
        }

        elements = temp;
        front = 0;
    }
}
