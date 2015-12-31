package edu.mit.ita.ch10;

public class ArrayQueue<T> implements Queue<T> {
    private final T[] elements;
    private int front;
    private int size;

    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
        this.elements = (T[])new Object[capacity];
        this.front = 0;
        this.size = 0;
    }

    @Override
    public void enqueue(T element) {
        if (isFull()) {
            throw new IllegalStateException("Queue overflow");
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

    public boolean isFull() {
        return size == elements.length;
    }
}
