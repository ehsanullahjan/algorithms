package edu.mit.ita.util

import spock.lang.Specification
import spock.lang.Unroll

import static edu.mit.ita.util.Arrays.*

class ArraysSpec extends Specification {
    private static final Random random = new Random();

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
        "insertion sort" | { insertionSort(it) } | randomSeq()
        "merge sort"     | { mergeSort(it) }     | randomSeq()
    }

    private def static Integer[] randomSeq(int n = 10, int bound = 100) {
        Integer[] nums = new Integer[n];
        nums.indices.each {nums[it] = random.nextInt(bound)}
        return nums
    }
}
