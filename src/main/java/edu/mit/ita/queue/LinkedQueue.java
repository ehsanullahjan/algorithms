package edu.mit.ita.queue;

import edu.mit.ita.adt.Queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<E> implements Queue<E> {
    private final Node<E> front;
    private Node<E> back;
    private int size;

    public LinkedQueue() {
        this.front = Node.empty();
        this.back = front;
        this.size = 0;
    }

    @Override
    public void enqueue(E element) {
        Node<E> node = new Node<>(element);
        back.next = node;
        back = node;
        size++;
    }

    @Override
    public E dequeue() {
        Node<E> node = peek0();
        front.next = node.next;
        size--;

        if (isEmpty())
            back = front;

        return node.element;
    }

    @Override
    public E peek() {
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
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> node = front.next;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                if (!hasNext())
                    throw new NoSuchElementException();

                E element = node.element;
                node = node.next;
                return element;
            }
        };
    }

    private Node<E> peek0() {
        if (isEmpty())
            throw new NoSuchElementException();

        return front.next;
    }

    private static final class Node<E> {
        final E element;
        Node<E> next;

        Node(E element) {
            this.element = element;
        }

        static <E> Node<E> empty() {
            return new Node<>(null);
        }
    }
}
