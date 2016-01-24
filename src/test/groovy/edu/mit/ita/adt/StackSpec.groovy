package edu.mit.ita.adt

import spock.lang.Specification

abstract class StackSpec extends Specification {
    def "A new stack starts empty"() {
        given:
        Stack<String> stack = newStack()

        expect:
        stack.isEmpty()
        stack.size() == 0
    }

    def "Last item pushed onto stack is the first item popped"() {
        given:
        Stack<String> stack = newStack()

        when:
        stack.push("Johny")
        stack.push("Manny")

        then:
        stack.pop() == "Manny"
        stack.size() == 1
    }

    def "Peeking does not remove item off the stack"() {
        given:
        Stack<String> stack = newStack()

        when:
        stack.push("Johny")
        stack.push("Manny")

        then:
        stack.peek() == "Manny"
        stack.size() == 2
    }

    def "Popping empty stack throws exception"() {
        given:
        Stack<String> stack = newStack()

        when:
        stack.pop()

        then:
        thrown IllegalStateException
    }

    def "Peeking empty stack throws exception"() {
        given:
        Stack<String> stack = newStack()

        when:
        stack.peek()

        then:
        thrown IllegalStateException
    }

    def "Can iterate stack in LIFO order"() {
        given:
        String[] elements = ["Johny", "Manny", "Tommy"]
        Stack<String> stack = newStack();
        elements.each {stack.push(it)}

        expect:
        int i = elements.length - 1;
        for (String element : stack) {
            element == elements[i--];
        }
    }

    protected abstract Stack<String> newStack();
}
