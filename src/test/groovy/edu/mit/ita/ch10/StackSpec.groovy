package edu.mit.ita.ch10

import spock.lang.Specification

class StackSpec extends Specification {
    def "A new stack starts empty"() {
        given:
        Stack<String> names = new FixedArrayStack<>(2);

        expect:
        names.isEmpty()
        names.size() == 0
    }

    def "Last item pushed onto stack is the first item popped"() {
        given:
        Stack<String> names = new FixedArrayStack<>(2);

        when:
        names.push("Johny")
        names.push("Manny")

        then:
        names.pop() == "Manny"
        names.size() == 1
    }

    def "Peeking does not remove item off the stack"() {
        given:
        Stack<String> names = new FixedArrayStack<>(2);

        when:
        names.push("Johny")
        names.push("Manny")

        then:
        names.peek() == "Manny"
        names.size() == 2
    }

    def "Popping empty stack throws exception"() {
        given:
        Stack<String> names = new FixedArrayStack<>(2);

        when:
        names.pop()

        then:
        thrown IllegalStateException
    }

    def "Peeking empty stack throws exception"() {
        given:
        Stack<String> names = new FixedArrayStack<>(2);

        when:
        names.peek()

        then:
        thrown IllegalStateException
    }
}
