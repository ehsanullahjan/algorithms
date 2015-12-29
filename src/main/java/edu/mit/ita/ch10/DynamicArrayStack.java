package edu.mit.ita.ch10;

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
        if (isFull()) {
            grow();
        }

        elements[++top] = element;
    }

    @Override
    public T pop() {
        T element = peek();
        elements[top--] = null;

        if (isQuarterFull()) {
            shrink();
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

    private boolean isFull() {
        return size() == elements.length;
    }

    private boolean isQuarterFull() {
        return size() == elements.length / 4;
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
        for (int i = 0; i <= top; i++) {
            temp[i] = elements[i];
        }

        elements = temp;
    }
}
