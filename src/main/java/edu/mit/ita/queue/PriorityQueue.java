package edu.mit.ita.queue;

import edu.mit.ita.adt.Queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static edu.mit.ita.util.Arrays.swap;
import static edu.mit.ita.util.Comparables.ge;
import static edu.mit.ita.util.Comparables.le;
import static java.lang.Math.max;
import static java.lang.System.arraycopy;

public abstract class PriorityQueue<E extends Comparable<? super E>> implements Queue<E> {
    private static final int DEFAULT_CAPACITY = 15;

    protected E[] elements;
    protected int size;

    protected PriorityQueue() {
        elements = newArray(DEFAULT_CAPACITY);
        size = 0;
    }

    protected PriorityQueue(E[] items) {
        elements = newArray(items.length);
        arraycopy(items, 0, elements, 0, items.length);
        size = items.length;

        heapify();
    }

    public static <E extends Comparable<? super E>> PriorityQueue<E> newMinPriorityQueue() {
        return new MinPriorityQueue<>();
    }

    public static <E extends Comparable<? super E>> PriorityQueue<E> newMinPriorityQueue(E[] items) {
        return new MinPriorityQueue<>(items);
    }

    public static <E extends Comparable<? super E>> PriorityQueue<E> newMaxPriorityQueue() {
        return new MaxPriorityQueue<>();
    }

    public static <E extends Comparable<? super E>> PriorityQueue<E> newMaxPriorityQueue(E[] items) {
        return new MaxPriorityQueue<>(items);
    }

    @Override
    public void enqueue(E element) {
        if (isFull())
            resize(elements.length * 2);

        elements[size++] = element;
        siftUp(size - 1);
    }

    @Override
    public E dequeue() {
        E element = peek();
        size--;

        if (!isEmpty()) {
            elements[0] = elements[size];
            elements[size] = null;
            siftDown(0);
        }
        return element;
    }

    @Override
    public E peek() {
        if (isEmpty())
            throw new NoSuchElementException();

        return elements[0];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayQueueIterator<>(elements, 0, size);
    }

    private void heapify() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    private void siftDown(int parent) {
        int candidate = dominant(dominant(parent, left(parent)), right(parent));
        if (parent != candidate) {
            swap(elements, parent, candidate);
            siftDown(candidate);
        }
    }

    private void siftUp(int child) {
        int parent = parent(child);
        if (parent != -1 && !isDominant(parent, child)) {
            swap(elements, parent, child);
            siftUp(parent);
        }
    }

    private int dominant(int parent, int child) {
        assert parent >= 0 && parent < size;

        if (child == -1)
            return parent;
        else if (isDominant(parent, child))
            return parent;
        else
            return child;
    }

    protected abstract boolean isDominant(int i, int j);

    private int parent(int child) {
        if (child == 0 || child >= size)
            return -1;
        else
            return (child - 1) / 2;
    }

    private int left(int parent) {
        return index(parent, 1);
    }

    private int right(int parent) {
        return index(parent, 2);
    }

    private int index(int node, int offset) {
        assert node >= 0 && node < size;

        int child = node * 2 + offset;
        return child < size ? child : -1;
    }

    private boolean isFull() {
        return size == elements.length;
    }

    private void resize(int capacity) {
        E[] temp = newArray(capacity);
        arraycopy(elements, 0, temp, 0, elements.length);
        elements = temp;
    }

    @SuppressWarnings("unchecked")
    private E[] newArray(int capacity) {
        return (E[])new Comparable<?>[max(capacity, DEFAULT_CAPACITY)];
    }

    private static final class MinPriorityQueue<E extends Comparable<? super E>> extends PriorityQueue<E> {
        public MinPriorityQueue() {
        }

        public MinPriorityQueue(E[] items) {
            super(items);
        }

        @Override
        protected boolean isDominant(int i, int j) {
            return le(elements[i], elements[j]);
        }
    }

    private static final class MaxPriorityQueue<E extends Comparable<? super E>> extends PriorityQueue<E> {
        public MaxPriorityQueue() {
            super();
        }

        public MaxPriorityQueue(E[] items) {
            super(items);
        }

        @Override
        protected boolean isDominant(int i, int j) {
            return ge(elements[i], elements[j]);
        }
    }
}
