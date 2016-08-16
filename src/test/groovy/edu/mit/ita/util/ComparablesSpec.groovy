package edu.mit.ita.util

import spock.lang.Specification

import static Comparables.max
import static Comparables.min
import static edu.mit.ita.util.Comparables.*

class ComparablesSpec extends Specification {
    private static final String[] seq = ["B", "F", "Z", "J", "X", "L", "Y", "M", "D"]

    def "Can find minimum in a comparable sequence"(int lo, int hi, String result) {
        expect:
        min(seq, lo, hi) == result

        where:
        lo | hi             || result
        0  | seq.length - 1 || "B"
        2  | 6              || "J"
    }

    def "Can find maximum in a comparable sequence"(int lo, int hi, String result) {
        expect:
        max(seq, lo, hi) == result

        where:
        lo | hi             || result
        0  | seq.length - 1 || "Z"
        3  | seq.length - 1 || "Y"
    }

    def "Comparable wrapper methods work as per Comparable contract"() {
        given:
        Integer zero = Integer.valueOf(0)
        Integer one = Integer.valueOf(1)

        expect:
        eq(zero, zero)
        !eq(zero, one)

        and:
        lt(zero, one)
        le(zero, one)
        le(zero, zero)

        and:
        gt(one, zero)
        ge(one, zero)
        ge(one, one)
    }
}
