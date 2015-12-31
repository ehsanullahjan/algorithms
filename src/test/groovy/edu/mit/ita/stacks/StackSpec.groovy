package edu.mit.ita.stacks

import spock.lang.Specification

class StackSpec extends Specification {
    def "A new stack starts empty"() {
        expect:
        stack.isEmpty()
        stack.size() == 0

        where:
        stack                   || dummy
        new FixedArrayStack(2)  || ""
        new DynamicArrayStack() || ""
        new LinkedStack()       || ""
    }

    def "Last item pushed onto stack is the first item popped"() {
        when:
        stack.push("Johny")
        stack.push("Manny")

        then:
        stack.pop() == "Manny"
        stack.size() == 1

        where:
        stack                   || dummy
        new FixedArrayStack(2)  || ""
        new DynamicArrayStack() || ""
        new LinkedStack()       || ""
    }

    def "Peeking does not remove item off the stack"() {
        when:
        stack.push("Johny")
        stack.push("Manny")

        then:
        stack.peek() == "Manny"
        stack.size() == 2

        where:
        stack                   || dummy
        new FixedArrayStack(2)  || ""
        new DynamicArrayStack() || ""
        new LinkedStack()       || ""
    }

    def "Popping empty stack throws exception"() {
        when:
        stack.pop()

        then:
        thrown IllegalStateException

        where:
        stack                   || dummy
        new FixedArrayStack(2)  || ""
        new DynamicArrayStack() || ""
        new LinkedStack()       || ""
    }

    def "Peeking empty stack throws exception"() {
        when:
        stack.peek()

        then:
        thrown IllegalStateException

        where:
        stack                   || dummy
        new FixedArrayStack(2)  || ""
        new DynamicArrayStack() || ""
        new LinkedStack()       || ""
    }
}