package edu.mit.ita.util

import spock.lang.Specification

import static Comparables.max
import static Comparables.min
import static Comparables.*

class ComparableUtilSpec extends Specification {
    private static final String[] seq = ["B", "F", "Z", "J", "X", "L", "Y", "M", "D"]

    def "Can find minimum in a comparable sequence"(int lo, int hi, String result) {
        expect:
        min(seq, lo, hi) == result

        where:
        lo | hi         || result
        0  | seq.length || "B"
        2  | 5          || "J"
    }

    def "Can find maximum in a comparable sequence"(int lo, int hi, String result) {
        expect:
        max(seq, lo, hi) == result

        where:
        lo | hi         || result
        0  | seq.length || "Z"
        3  | seq.length || "Y"
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
        ltOrEq(zero, one)
        ltOrEq(zero, zero)

        and:
        gt(one, zero)
        gtOrEq(one, zero)
        gtOrEq(one, one)
    }
}
