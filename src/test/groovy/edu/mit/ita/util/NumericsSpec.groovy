package edu.mit.ita.util

import spock.lang.Specification

class NumericsSpec extends Specification {
    long[] facts = [1, 1, 2, 6, 24, 120, 720, 5040, 5040, 40320]
    long[] fibs = [0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144]

    def "Can find n-factorial"() {
        expect:
        (0..<facts.length).each { Numerics.factorial(it) == facts[it] }
    }

    def "Can find nth fibonacci"() {
        expect:
        (0..<fibs.length).each { Numerics.fibonacci(it) == fibs[it] }
    }
}
