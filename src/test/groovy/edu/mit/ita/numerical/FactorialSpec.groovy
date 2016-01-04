package edu.mit.ita.numerical

import edu.mit.ita.numerical.Factorial
import spock.lang.Specification

class FactorialSpec extends Specification {
    long[] facts = [1, 1, 2, 6, 24, 120, 720, 5040, 5040, 40320, 362880, 3628800]

    def "Can find factorial recursively"() {
        expect:
        (0..10).each {Factorial.recursive(it) == facts[it]}
    }

    def "Can find factorial imperatively"() {
        expect:
        (0..10).each {Factorial.imperative(it) == facts[it]}
    }
}