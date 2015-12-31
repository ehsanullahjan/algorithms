package edu.mit.ita.stacks;

import edu.mit.ita.adt.Stack;

public class FixedArrayStack<T> implements Stack<T> {
    private final T[] elements;
    private int top;

    @SuppressWarnings("unchecked")
    public FixedArrayStack(int capacity) {
        this.elements = (T[])new Object[capacity];
        this.top = -1;
    }

    @Override
    public void push(T element) {
        if (isFull()) {
            throw new IllegalStateException("Stack overflow");
        }

        elements[++top] = element;
    }

    @Override
    public T pop() {
        T element = peek();
        elements[top--] = null;
        return element;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack underflow");
        }

        return elements[top];
    }

    @Override
    public int size() {
        return top + 1;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean isFull() {
        return size() == elements.length;
    }
}
