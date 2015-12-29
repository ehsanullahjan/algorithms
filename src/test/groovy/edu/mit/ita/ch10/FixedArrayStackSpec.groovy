package edu.mit.ita.ch10

import spock.lang.Specification

class FixedArrayStackSpec extends Specification {
    def "Fixed array stack throws on push when full"() {
        given: "a full stack"
        Stack<String> stack = new FixedArrayStack(1);
        stack.push("Johny")

        when: "an item is pushed onto the stack"
        stack.push("Manny")

        then: "an exception is thrown"
        thrown IllegalStateException
    }
}
