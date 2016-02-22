package edu.mit.ita.heap;

import edu.mit.ita.adt.Heap;

import java.util.NoSuchElementException;

import static edu.mit.ita.util.Arrays.swap;
import static edu.mit.ita.util.Comparables.ge;
import static edu.mit.ita.util.Comparables.gt;
import static edu.mit.ita.util.Comparables.lt;

public class BinaryHeap<T extends Comparable<? super T>> implements Heap<T> {
    private T[] elements;
    private int size;

    @SuppressWarnings("unchecked")
    public BinaryHeap() {
        this(8);
    }

    @SuppressWarnings("unchecked")
    public BinaryHeap(int initialCapacity) {
        this.elements = (T[])new Comparable[initialCapacity];
        this.size = 0;
    }

    public static <T extends Comparable<? super T>> BinaryHeap<T> minHeap() {
        return null;
    }

    public static <T extends Comparable<? super T>> BinaryHeap<T> maxHeap() {
        return null;
    }

    public static void main(String[] args) {
        BinaryHeap<Integer> heap = new BinaryHeap<>();
        heap.insert(1);
        heap.insert(3);
        heap.insert(2);
        heap.insert(4);
        heap.insert(5);

        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
    }

    @Override
    public void insert(T element) {
        if (loadFactor() >= 1.0F) {
            resize(capacity() * 2);
        }

        elements[size] = element;
        siftUp(size++);
    }

    @Override
    public T remove() {
        T element = peek();
        elements[0] = elements[--size];
        elements[size] = null;
        if (!isEmpty()) {
            siftDown(0);
        }
        return element;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("PriorityQueue underflow");
        }

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

    private void siftUp(int index) {
        if (index == 0) {
            return;
        }

        int parent = parent(index);
        if (gt(elements[index], elements[parent])) {
            swap(elements, index, parent);
            siftUp(parent);
        }
    }

    private void siftDown(int index) {
        assert index >= 0 && index <= size : index;

        if (isLeaf(index)) {
            return;
        }

        int max = maxChild(index);
        if (lt(elements[index], elements[max])) {
            swap(elements, index, max);
            siftDown(max);
        }
    }

    private boolean isInternal(int index) {
        return index >= 0 && index <= size / 2 - 1;
    }

    private boolean isLeaf(int index) {
        return index > size / 2 - 1 && index < size;
    }

    private int parent(int index) {
        if (index <= 0 || index >= size) {
            return -1;
        }

        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        int left = 2 * index + 1;
        return left < size ? left : -1;
    }

    private int rightChild(int index) {
        int right = 2 * index + 2;
        return right < size ? right : -1;
    }

    // Note: Since heap is a complete BT, we can't have right child unless we also have left child.
    private int maxChild(int index) {
        int left = leftChild(index);
        if (left == -1) {
            return left;
        }

        int right = rightChild(index);
        if (right == -1) {
            return left;
        }

        return ge(elements[left], elements[right])
               ? left
               : right;
    }

    private int capacity() {
        return elements.length;
    }

    private float loadFactor() {
        return (float)size / capacity();
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        T[] temp = (T[])new Comparable[capacity];
        System.arraycopy(elements, 0, temp, 0, size);
        elements = temp;
    }
}
