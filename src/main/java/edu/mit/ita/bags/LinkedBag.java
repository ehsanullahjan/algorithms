package edu.mit.ita.bags;

import edu.mit.ita.adt.Collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedBag<T> implements Collection<T> {
    private final Node<T> head;
    private int size;

    public LinkedBag() {
        this.head = Node.empty();
        this.size = 0;
    }

    @Override
    public void add(T element) {
        Node<T> node = new Node<>(element);
        node.next = head.next;
        head.next = node;
        size++;
    }

    @Override
    public boolean remove(T element) {
        if (element == null || isEmpty()) {
            return false;
        }

        Node<T> i = head;
        Node<T> j = head.next;
        while (j != null) {
            if (element.equals(j.element)) {
                i.next = j.next;
                size--;
                return true;
            }

            i = j;
            j = j.next;
        }

        return false;
    }

    @Override
    public boolean contains(T element) {
        if (element == null) {
            return false;
        }

        for (Node<T> node = head.next; node != null; node = node.next) {
            if (element.equals(node.element)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void clear() {
        head.next = null;
        size = 0;
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
            private Node<T> current = head.next;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                Node<T> x = current;
                current = current.next;
                return x.element;
            }
        };
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
