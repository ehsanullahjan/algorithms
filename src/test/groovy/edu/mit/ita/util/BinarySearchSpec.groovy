package edu.mit.ita.util

import spock.lang.Specification

class BinarySearchSpec extends Specification {
    def "Can find number in sorted array using binary search"() {
        given:
        Integer[] nums = [4, 5, 18, 23, 34, 51, 56, 67, 68, 91]

        expect:
        BinarySearch.rank(nums, key) == rank

        where:
        key || rank
        4   || 0
        5   || 1
        34  || 4
        68  || 8
        91  || 9
    }
}
