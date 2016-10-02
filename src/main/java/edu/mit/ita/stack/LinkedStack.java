package edu.mit.ita.stack;

import edu.mit.ita.adt.Stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<E> implements Stack<E> {
    private final Node<E> head;
    private int size;

    public LinkedStack() {
        head = Node.empty();
        size = 0;
    }

    @Override
    public void push(E element) {
        Node<E> top = new Node<>(element);
        top.next = head.next;
        head.next = top;
        size++;
    }

    @Override
    public E pop() {
        Node<E> top = peek0();
        head.next = top.next;
        size--;

        return top.element;
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
            private Node<E> top = head.next;

            @Override
            public boolean hasNext() {
                return top != null;
            }

            @Override
            public E next() {
                if (!hasNext())
                    throw new NoSuchElementException();

                E element = top.element;
                top = top.next;
                return element;
            }
        };
    }

    private Node<E> peek0() {
        if (isEmpty())
            throw new NoSuchElementException();

        return head.next;
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
