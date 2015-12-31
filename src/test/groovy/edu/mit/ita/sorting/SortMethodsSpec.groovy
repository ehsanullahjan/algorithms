package edu.mit.ita.sorting

import spock.lang.Specification
import spock.lang.Unroll

import static SortMethods.insertionSort

class SortMethodsSpec extends Specification {
    private static final Integer[] unorderedSeq = [7, 10, 5, 3, 8, 4, 2, 9, 6];
    private static final Integer[] orderedSeq = [2, 3, 4, 5, 6, 7, 8, 9, 10];

    @Unroll
    @SuppressWarnings("GroovyAssignabilityCheck")
    def "Items can be sorted using #method"() {
        when: "#method is applied to unsorted items"
        sort(items)

        then: "the items are sorted in place"
        items == result

        where:
        method           | sort                    | items                || result
        "insertion sort" | {a -> insertionSort(a)} | unorderedSeq.clone() || orderedSeq
    }
}
