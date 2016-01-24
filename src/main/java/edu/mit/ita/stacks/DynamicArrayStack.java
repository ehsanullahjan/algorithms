package edu.mit.ita.stacks;

import edu.mit.ita.adt.Stack;

import java.util.Iterator;

public class DynamicArrayStack<T> implements Stack<T> {
    private T[] elements;
    private int top;

    public DynamicArrayStack() {
        this(16);
    }

    @SuppressWarnings("unchecked")
    public DynamicArrayStack(int initialCapacity) {
        this.elements = (T[])new Object[initialCapacity];
        this.top = -1;
    }

    @Override
    public void push(T element) {
        if (loadFactor() >= 1.0F) {
            resize(capacity() * 2);
        }

        elements[++top] = element;
    }

    @Override
    public T pop() {
        T element = peek();
        elements[top--] = null;

        if (loadFactor() <= 0.25) {
            resize(capacity() / 2);
        }

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

    public int capacity() {
        return elements.length;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayStackIterator<>(elements, top);
    }

    private float loadFactor() {
        return size() / capacity();
    }

    @SuppressWarnings({"unchecked", "ManualArrayCopy"})
    private void resize(int capacity) {
        T[] temp = (T[])new Object[capacity];
        for (int i = 0; i <= top; i++) {
            temp[i] = elements[i];
        }

        elements = temp;
    }
}
