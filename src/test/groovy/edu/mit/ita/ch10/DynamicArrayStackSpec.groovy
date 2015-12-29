package edu.mit.ita.ch10

import spock.lang.Specification

class DynamicArrayStackSpec extends Specification {
    def "Dynamic array stack grows on push when full"() {
        given: "a full stack"
        int initialCapacity = 4
        Stack<String> stack = new DynamicArrayStack<>(initialCapacity);
        stack.push("Johny")
        stack.push("Manny")
        stack.push("Abby")
        stack.push("Haley")

        when: "an item is pushed onto the stack"
        stack.push("Tommy")

        then: "the stack grows to double its size"
        stack.size() == 5
        stack.capacity() == initialCapacity * 2
    }

    def "Dynamic array stack shrinks when full below 1/4th its capacity"() {
        given: "a half full stack"
        int initialCapacity = 4
        Stack<String> stack = new DynamicArrayStack<>(initialCapacity);
        stack.push("Johny")
        stack.push("Manny")

        when: "stack consumption drops to 1/4th its capacity"
        stack.pop()

        then: "the stack shrinks to half its size"
        stack.size() == 1
        stack.capacity() == initialCapacity / 2 as int
    }
}
