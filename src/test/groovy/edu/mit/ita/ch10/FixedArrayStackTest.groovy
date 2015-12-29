package edu.mit.ita.ch10

import spock.lang.Specification

class FixedArrayStackTest extends Specification {
    def "Fixed array stack throws on push when full"() {
        given: "a full stack"
        Stack<String> names = new FixedArrayStack(1);
        names.push("Johny")

        when: "an item is pushed onto the stack"
        names.push("Manny")

        then: "an exception is thrown"
        thrown IllegalStateException
    }
}
