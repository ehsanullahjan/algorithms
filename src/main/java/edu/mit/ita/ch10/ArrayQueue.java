package edu.mit.ita.ch10;

public class ArrayQueue<T> implements Queue<T> {
    private final T[] elements;
    private int size;
    private int head;
    private int tail;

    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
        this.elements = (T[])new Object[capacity];
        this.size = 0;
        this.head = 0;
        this.tail = 0;
    }

    @Override
    public void enqueue(T element) {
        if (isFull()) {
            throw new IllegalStateException("Queue overflow");
        }

        elements[tail] = element;
        tail = (tail + 1) % elements.length;
        size++;
    }

    @Override
    public T dequeue() {
        T element = peek();
        elements[head] = null;
        head = (head + 1) % elements.length;
        size--;

        return element;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue underflow");
        }

        return elements[head];
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
