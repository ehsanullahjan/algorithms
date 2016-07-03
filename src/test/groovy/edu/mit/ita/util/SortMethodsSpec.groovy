package edu.mit.ita.util

import spock.lang.Specification
import spock.lang.Unroll

import static edu.mit.ita.util.Comparables.le
import static edu.mit.ita.util.SortMethods.insertionSort
import static edu.mit.ita.util.SortMethods.mergeSort

class SortMethodsSpec extends Specification {
    private static final Random random = new Random();

    @Unroll
    def "Items can be sorted using #method"() {
        when: "#method is applied to unsorted items"
        sort(items)

        then: "the items are sorted in place"
        isSorted(items)

        where:
        method           | sort                | items
        "insertion sort" | {insertionSort(it)} | randomSeq()
        "merge sort"     | {mergeSort(it)}     | randomSeq()
    }

    private def static Integer[] randomSeq(int n = 10, int bound = 100) {
        Integer[] nums = new Integer[n];
        nums.indices.each {nums[it] = random.nextInt(bound)}
        return nums
    }

    private def static <T extends Comparable<? super T>> boolean isSorted(T[] seq) {
        (0..<seq.length - 1).every {le(seq[it], seq[it + 1])}
    }
}
