package edu.mit.ita.util

import spock.lang.Specification
import spock.lang.Unroll

import static edu.mit.ita.util.Arrays.*

class ArraysSpec extends Specification {
    private static final int MAX_SEQ_SIZE = 100;

    def "Can find number in sorted array using binary search"() {
        given:
        Integer[] nums = [4, 5, 18, 23, 34, 51, 56, 67, 68, 91]

        expect:
        binarySearch(nums, key) == rank

        where:
        key || rank
        4   || 0
        5   || 1
        34  || 4
        68  || 8
        91  || 9
    }

    @Unroll
    def "Items can be sorted using #method"() {
        when: "#method is applied to unsorted items"
        sort(items)

        then: "the items are sorted in place"
        isSorted(items)

        where:
        method           | sort                  | items
        "insertion sort" | { insertionSort(it) } | randomIntSeq(MAX_SEQ_SIZE)
        "merge sort"     | { mergeSort(it) }     | randomIntSeq(MAX_SEQ_SIZE)
        "quick sort"     | { quickSort(it) }     | randomIntSeq(MAX_SEQ_SIZE)
    }

    def "Can shuffle a sorted sequence"() {
        given:
        Integer[] seq = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        when:
        shuffle(seq)

        then:
        int threshold = 3;
        int stickies = 0;
        seq.eachWithIndex { element, index -> if (element == index) stickies++ }
        stickies <= threshold
    }
}
