package edu.mit.ita.queue;

import edu.mit.ita.adt.Queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

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

        if (isEmpty())
            back = front;

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

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> node = front.next;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();

                T element = node.element;
                node = node.next;
                return element;
            }
        };
    }

    private Node<T> peek0() {
        if (isEmpty())
            throw new NoSuchElementException();

        return front.next;
    }

    private static final class Node<T> {
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
