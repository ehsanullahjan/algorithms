package edu.mit.ita.stack

import edu.mit.ita.adt.Stack
import edu.mit.ita.adt.StackSpec

class LinkedStackSpec extends StackSpec {
    @Override
    protected Stack<String> newStack() {
        return new LinkedStack<>()
    }
}
