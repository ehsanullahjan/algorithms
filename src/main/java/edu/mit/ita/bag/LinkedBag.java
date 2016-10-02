package edu.mit.ita.bag;

import edu.mit.ita.adt.Collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedBag<E> implements Collection<E> {
    private final Node<E> head;
    private int size;

    public LinkedBag() {
        this.head = Node.sentinel();
        this.size = 0;
    }

    @Override
    public void add(E element) {
        Node<E> node = new Node<>(element);
        node.next = head.next;
        head.next = node;
        size++;
    }

    @Override
    public boolean remove(E element) {
        if (element == null || isEmpty())
            return false;

        Node<E> i = head;
        Node<E> j = head.next;
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
    public boolean contains(E element) {
        if (element == null)
            return false;

        for (Node<E> node = head.next; node != null; node = node.next) {
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
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head.next;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext())
                    throw new NoSuchElementException();

                Node<E> x = current;
                current = current.next;
                return x.element;
            }
        };
    }

    private static final class Node<E> {
        final E element;
        Node<E> next;

        Node(E element) {
            this.element = element;
        }

        static <E> Node<E> sentinel() {
            return new Node<>(null);
        }
    }
}
