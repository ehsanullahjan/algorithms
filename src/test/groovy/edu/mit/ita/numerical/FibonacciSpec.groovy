package edu.mit.ita.numerical

import spock.lang.Specification

class FibonacciSpec extends Specification {
    long[] fibs = [0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144]

    def "Can find fibonacci recursively"() {
        expect:
        (0..<fibs.length).each {Fibonacci.recursive(it) == fibs[it]}
    }

    def "Can find fibonacci dynamically"() {
        expect:
        (0..<fibs.length).each {Fibonacci.dynamic(it) == fibs[it]}
    }
}
