package edu.mit.ita.stack

import edu.mit.ita.adt.Stack
import edu.mit.ita.adt.StackSpec

class FixedArrayStackSpec extends StackSpec {
    def "Fixed array stack throws on push when full"() {
        given: "a full stack"
        Stack<String> stack = new FixedArrayStack(1);
        stack.push("Johny")

        when: "an item is pushed onto the stack"
        stack.push("Manny")

        then: "an exception is thrown"
        thrown IllegalStateException
    }

    @Override
    protected Stack<String> newStack() {
        return new FixedArrayStack<String>(16)
    }
}
