package edu.mit.ita.queues;

import edu.mit.ita.adt.Queue;

public class LinkedQueue<T> implements Queue<T> {
    private final Node<T> front;
    private Node<T> back;
    private int size;

    public LinkedQueue() {
        this.front = Node.empty();
        this.back = front;
        this.size = 0;
    }

    @Override
    public void enqueue(T element) {
        Node<T> node = new Node<>(element);
        back.next = node;
        back = node;
        size++;
    }

    @Override
    public T dequeue() {
        Node<T> node = peek0();
        front.next = node.next;
        size--;

        if (isEmpty()) {
            back = front;
        }

        return node.element;
    }

    @Override
    public T peek() {
        return peek0().element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Node<T> peek0() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue underflow");
        }

        return front.next;
    }

    private static class Node<T> {
        final T element;
        Node<T> next;

        Node(T element) {
            this.element = element;
        }

        static <T> Node<T> empty() {
            return new Node<>(null);
        }
    }
}
