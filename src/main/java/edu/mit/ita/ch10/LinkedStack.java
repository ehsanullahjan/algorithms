package edu.mit.ita.ch10;

public class LinkedStack<T> implements Stack<T> {
    private final Node<T> head;
    private int size;

    public LinkedStack() {
        head = Node.empty();
        size = 0;
    }

    @Override
    public void push(T element) {
        Node<T> top = new Node<>(element);
        top.next = head.next;
        head.next = top;
        size++;
    }

    @Override
    public T pop() {
        Node<T> top = peek0();
        head.next = top.next;
        size--;

        return top.element;
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

    private Node<T> peek0() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack underflow");
        }

        return head.next;
    }
}
